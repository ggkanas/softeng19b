package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.data.DataAccess;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.restlet.data.Status;
import org.restlet.util.Series;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ResourceException;






public class UserManager extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        //read existing user

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        try{
        if (!dataAccess.checkToken(token))
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        if(!dataAccess.isAdmin(token)) {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

        String username = getMandatoryAttribute("username", "Username is missing");

        try {
        Optional<User> optional = dataAccess.getUser(username);
        User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_FORBIDDEN));

        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("requestsPerDayQuota", user.getRequestsPerDayQuota());
        map.put("period", user.getPeriod());
        map.put("remainingRequests", user.getRemainingRequests());
        map.put("token", user.getToken());

        return new JsonMapRepresentation(map);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }
    }


    @Override
    protected Representation put(Representation entity) throws ResourceException {
        //update existing user

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        try {
        if (!dataAccess.checkToken(token))
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        if(!dataAccess.isAdmin(token)) {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

        String username = getMandatoryAttribute("username", "Username is missing");

        try {
        Optional<User> optional = dataAccess.getUser(username);
        User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_FORBIDDEN));

        Map<String, Object> map = new HashMap<>();

          Form form = new Form(entity);

          user.setPassword(form.getFirstValue("password"));
          user.setEmail(form.getFirstValue("email"));
          user.setRequestsPerDayQuota(Integer.parseInt(form.getFirstValue("quotas")));

          dataAccess.updateUser(user);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

          return new EmptyRepresentation();

    }
}
