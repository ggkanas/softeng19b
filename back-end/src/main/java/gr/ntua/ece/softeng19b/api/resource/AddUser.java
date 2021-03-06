package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.data.DataAccess;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Status;
import org.restlet.util.Series;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ResourceException;

public class AddUser extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

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

        Form form = new Form(entity);
        User user = new User();
        String username = form.getFirstValue("username");
        user.setPassword(form.getFirstValue("password"));
        user.setEmail(form.getFirstValue("email"));
        user.setRequestsPerDayQuota(Integer.parseInt(form.getFirstValue("quotas")));
        user.setRemainingRequests(user.getRequestsPerDayQuota());

        // πρ΄έπει να προστεθεί και έλεγχος για τον χρ΄ήστη
        try {
            if (dataAccess.addUser(user) == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("alreadyExists", "true");
                return new JsonMapRepresentation(map);
            };
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

        return new EmptyRepresentation();
    }
}
