package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.api.AuthenticationService;
import gr.ntua.ece.softeng19b.api.representation.*;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Status;
import org.restlet.util.Series;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;
import java.io.BufferedReader;
import java.io.StringReader;

public class ImportDayAheadTotalLoadForecastDataSet extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Form form = new Form(entity);

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        try {
            if (!dataAccess.checkToken(token))
                throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
            } catch (Exception e) {
                throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
            }

        //if (!dataAccess.checkToken(form.getFirstValue("X-OBSERVATORY-AUTH"))) //to be confirmed
        //    throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        String csvFile = form.getFirstValue("file");
        int totalRecordsInFile = 0;
        int totalRecordsImported = 0;
        int totalRecordsInDatabase;

        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        br = new BufferedReader(new StringReader(csvFile));

        try {
            line = br.readLine();
            line.split(csvSplitBy);

            while((line = br.readLine()) != null) {
                totalRecordsInFile = totalRecordsInFile + 1;
                String[] dataLine = line.split(csvSplitBy);

                if(dataAccess.addDayAheadTotalLoadForecast(dataLine) == 1) totalRecordsImported = totalRecordsImported + 1;
                else break;
            }


            totalRecordsInDatabase = dataAccess.getTotalRecordsInDatabase("ActualTotalLoad");

        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("totalRecordsInFile", totalRecordsInFile);
        map.put("totalRecordsImported", totalRecordsImported);
        map.put("totalRecordsInDatabase", totalRecordsInDatabase);

        return new JsonMapRepresentation(map);
    }
}
