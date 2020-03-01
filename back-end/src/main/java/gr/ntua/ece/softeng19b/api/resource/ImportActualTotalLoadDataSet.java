package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class ImportActualTotalLoadDataSet extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Form form = new form(entity);

        if (!dataAccess.checkToken(form.getFirstValue("X-OBSERVATORY-AUTH"))) //to be confirmed
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

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

          if(dataAccess.AddActualTotalLoadRecord(dataLine) == 1) totalRecordsImported = totalRecordsImported + 1;
          else break;
        }

        int totalRecordsInDatabase = dataAccess.getTotalRecordsInDatabase("ActualTotalLoad");

        Format format = parseFormat(getQueryValue("format"));

        return format.generateRepresentation(new ImportRecordData(totalRecordsInFile,
            totalRecordsImported, totalRecordsInDataBase));
    }
}
