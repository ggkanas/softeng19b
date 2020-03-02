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
import java.io.FileReader;

public class ImportAggregatedGenerationPerTypeDataSet extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Form form = new Form(entity);

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        if (!dataAccess.checkToken(token))
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        //if (!dataAccess.checkToken(form.getFirstValue("X-OBSERVATORY-AUTH"))) //to be confirmed
        //    throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        String csvFile = form.getFirstValue("file");
        int totalRecordsInFile = 0;
        int totalRecordsImported = 0;

        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        br = new BufferedReader(new FileReader(csvFile));

        line = br.readLine();
        String[] dataLine = line.split(csvSplitBy);

        while((line = br.readLine()) != null) {
          totalRecordsInFile = totalRecordsInFile + 1;
          String[] dataLine = line.split(csvSplitBy);

          if(dataAccess.addActualAggregatedGenerationPerTypeRecord(dataLine) == 1) totalRecordsImported = totalRecordsImported + 1;
          else break;
        }

        int totalRecordsInDatabase = dataAccess.getTotalRecordsInDatabase("ActualTotalLoad");

        Map<String, Object> map = new HashMap<>();
        map.put("totalRecordsInFile", totalRecordsInFile);
        map.put("totalRecordsImported", totalRecordsImported);
        map.put("totalRecordsInDatabase", totalRecordsInDatabase);

        return JsonMapRepresentation(map);
    }
}
