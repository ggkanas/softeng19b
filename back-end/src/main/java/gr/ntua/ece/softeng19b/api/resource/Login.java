package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.api.AuthenticationService; //θεωρητικά πρέπει να υπάρχει κάτι τέτοιο
import gr.ntua.ece.softeng19b.api.representation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import gr.ntua.ece.softeng19b.api.resource.EnergyResource;


public class Login extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Form form = new Form(entity);

        String username = form.getFirstValue("username");
        String password = form.getFirstValue("password");

        Optional<User> optional = dataAccess.getUserName(username);
        User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "User: " + username + " not found")); //ίσως το exception να πρέπει να έχει άλλη μορφή

        if (!user.getPassword().equals(password))
          throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid password");

        do {
            String token = AuthenticationService.createToken();
        } while (checkToken(token));
        dataAccess.createToken(token, username);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);

        return new JsonMapRepresentation(map);
    }
}
