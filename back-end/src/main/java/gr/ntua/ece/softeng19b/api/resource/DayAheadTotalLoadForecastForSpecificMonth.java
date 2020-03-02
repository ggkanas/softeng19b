package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class DayAheadTotalLoadForecastForSpecificMonth extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date attribute
        String dateParam = getAttributeDecoded("month");

        YearMonth month = YearMonth.parse(dateParam);

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<DATLFRecordForSpecificMonth> result = dataAccess.fetchDayAheadTotalLoadForecastForSpecificMonth(
                    areaName,
                    resolution,
                    month
            );
            return format.generateRepresentation(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
