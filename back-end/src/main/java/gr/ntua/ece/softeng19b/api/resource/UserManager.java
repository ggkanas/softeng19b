package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.data.DataAccess;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ResourceException;






public class UserManager extends EnergyResource {

    @Override
    protected Representation get() throws ResourceException {
        //read existing user

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        if (!dataAccess.checkToken(token))
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        if(!dataAccess.isAdmin(token)) {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }

        String username = getMandatoryAttribute("username", "Username is missing");

        User user = dataAccess.getUser(username);

        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("requestsPerDayQuota", user.getRequestsPerDayQuota());
        map.put("period", user.getPeriod());
        map.put("remainingRequests", user.getRemainingRequests());
        map.put("token", user.getToken());

        return JsonMapRepresantation(map);

        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }


    @Override
    protected Representation put(Representation entity) throws ResourceException {
        //update existing user

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        if (!dataAccess.checkToken(token))
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        if(!dataAccess.isAdmin(token)) {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }

        String username = getMandatoryAttribute("username", "Username is missing");

        User user = dataAccess.getUser(username);

        Map<String, Object> map = new HashMap<>();

        if(userExists) {
          Form form = new form(entity);

          user.setPassword(form.getFirstValue("password"));
          user.setEmail(form.getFirstValue("email"));
          user.setRequestsPerDayQuota(Integer.parseInt(form.getFirstValue("quotas")));

          dataAccess.updateUser(user);

          return EmptyRepresantation();
        }

        else {

        }
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
