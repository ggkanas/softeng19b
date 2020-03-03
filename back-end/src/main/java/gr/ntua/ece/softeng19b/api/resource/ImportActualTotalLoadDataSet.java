package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.api.AuthenticationService;
import gr.ntua.ece.softeng19b.api.representation.*;
//import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;


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
import java.io.File;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * A generic json representation of a java.util.Map object.
 */
class JsonMapRepresentation extends WriterRepresentation {

    private final Map<String, Object> map;

    public JsonMapRepresentation(Map<String, Object> map) {
        super(MediaType.APPLICATION_JSON);
        this.map = map;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(map));
    }
}


public class ImportActualTotalLoadDataSet extends EnergyResource {

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

        //String csvFile;
        int totalRecordsInFile = 0;
        int totalRecordsImported = 0;

        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        int totalRecordsInDatabase;

        // 1/ Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1000240);

        // 2/ Create a new file upload handler
        RestletFileUpload upload = new RestletFileUpload(factory);
        FileItem item;
        List<FileItem> items;
        File file;
        // 3/ Request is parsed by the handler which generates a list of FileItems
        try {

            items = upload.parseRepresentation(entity);
        }catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }
        try {
            item = items.get(0);
            item.getFieldName();
            String tempDir = System.getProperty("java.io.tmpdir");
            file = new File(tempDir + File.separator + "file.txt");
            item.getInputStream();
            item.write(file);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "First Message " + e.getMessage(), e);
        }



        try {
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            line.split(csvSplitBy);
            while((line = br.readLine()) != null) {
                totalRecordsInFile = totalRecordsInFile + 1;
                String[] dataLine = line.split(csvSplitBy);

                if(dataAccess.addActualTotalLoadRecord(dataLine) == 1) totalRecordsImported = totalRecordsImported + 1;
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
