package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.data.DataAccess;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
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
        String username = getMandatoryAttribute("username", "Username is missing");

        boolean userExists = jdbcTemplate.queryForObject("select count(*) from User where Username = ?", new Object[] { username }, (ResultSet rs, int rownum) -> {
          return (rs.getInt(1) > 0);
        });

        Map<String, Object> map = new HashMap<>();

        if(userExists) {
          Form form = new form(entity);

          String newPassw = form.getFirstValue("password");
          String newEmail = form.getFirstValue("email");
          int newQuota = Integer.parseInt(form.getFirstValue("quotas"));

          int rows = jdbcTemplate.update("UPDATE User SET Password = ?, Email = ?, RequestsPerDayQuota = ? WHERE Username = ? ", new Object[] {newPassw, newEmail, newQuota, username});

          return JsonMapRepresantation(map);
        }

        else {

        }
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
