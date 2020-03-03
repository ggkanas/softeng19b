package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.model.*;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    Representation generateRepresentation1a(List<ATLRecordForSpecificDay> result);

    Representation generateRepresentation1b(List<ATLRecordForSpecificMonth> result);

    Representation generateRepresentation1c(List<ATLRecordForSpecificYear> result);

    Representation generateRepresentation2a(List<AGPerTypeRecordForSpecificDay> result);

    Representation generateRepresentation2b(List<AGPerTypeRecordForSpecificMonth> result);

    Representation generateRepresentation2c(List<AGPerTypeRecordForSpecificYear> result);

    Representation generateRepresentation3a(List<DayAheadTLForecastRecordForSpecificDay> result);

    Representation generateRepresentation3b(List<DayAheadTLForecastRecordForSpecificMonth> result);

    Representation generateRepresentation3c(List<DayAheadTLForecastRecordForSpecificYear> result);

    Representation generateRepresentation4a(List<AVSFRecordForSpecificDay> result);

    Representation generateRepresentation4b(List<AVSFRecordForSpecificMonth> result);

    Representation generateRepresentation4c(List<AVSFRecordForSpecificYear> result);

}
