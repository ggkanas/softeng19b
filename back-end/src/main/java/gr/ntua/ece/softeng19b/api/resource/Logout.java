package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.api.AuthenticationService;


import org.restlet.util.Series;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


public class Logout extends EnergyResource {

    public static final String AUTHENTICATION_HEADER = "X-OBSERVATORY-AUTH";
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();


    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue(AUTHENTICATION_HEADER);

        try {
            if (!dataAccess.checkToken(token))
                throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
            dataAccess.destroyToken(token);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }


        return new EmptyRepresentation();
    }
}
