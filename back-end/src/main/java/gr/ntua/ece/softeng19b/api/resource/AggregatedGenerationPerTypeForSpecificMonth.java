package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.AGPerTypeRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.util.Series;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.YearMonth;
import java.time.LocalDate;
import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class AggregatedGenerationPerTypeForSpecificMonth extends EnergyResource {

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
        String productionType = getMandatoryAttribute("ProductionType", "ProductionType is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date attribute
        String dateParam = getAttributeDecoded("month");

        YearMonth yearMonth = YearMonth.parse(dateParam);

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<AGPerTypeRecordForSpecificMonth> result = dataAccess.fetchAggregatedGenerationPerTypeForSpecificMonth(
                    areaName,
                    resolution,
                    productionType,
                    yearMonth
            );

            if (result.isEmpty()) throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN);

            return format.generateRepresentation2b(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
