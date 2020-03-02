package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.util.Series;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class ActualTotalLoadForSpecificYear extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        Series headers = (Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH"); //to be confirmed

        if (!dataAccess.checkToken(token))
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);

        if (!dataAccess.hasRemaining(token))
            throw new ResourceException(Status.CLIENT_ERROR_PAYMENT_REQUIRED);
        dataAccess.changeRemaining(token);


        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date attribute
        String dateParam = getAttributeDecoded("year");

        //Use the EnergyResource.parseXXX methods to parse the dates and implement the required business logic
        //For the sake of this example, we hard-code a date
        //LocalDate date = LocalDate.of(2019, 10, 1);

        Integer year = parseYear(dateParam);

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<ATLRecordForSpecificYear> result = dataAccess.fetchActualTotalLoadForSpecificYear(
                    areaName,
                    resolution,
                    year
            );

            if (result.isEmpty()) throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN);

            return format.generateRepresentation1c(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
