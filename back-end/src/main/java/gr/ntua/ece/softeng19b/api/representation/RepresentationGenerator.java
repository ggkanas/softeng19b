package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.model.*;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    Representation generateRepresentation(List<ATLRecordForSpecificDay> result);

    Representation generateRepresentation(List<ATLRecordForSpecificMonth> result);

    Representation generateRepresentation(List<ATLRecordForSpecificYear> result);

    Representation generateRepresentation(List<AGPerTypeRecordForSpecificDay> result);

    Representation generateRepresentation(List<AGPerTypeRecordForSpecificMonth> result);

    Representation generateRepresentation(List<AGPerTypeRecordForSpecificYear> result);

    Representation generateRepresentation(List<DayAheadTLForecastRecordForSpecificDay> result);

    Representation generateRepresentation(List<DayAheadTLForecastRecordForSpecificMonth> result);

    Representation generateRepresentation(List<DayAheadTLForecastRecordForSpecificYear> result);

    Representation generateRepresentation(List<AVSFRecordForSpecificDay> result);

    Representation generateRepresentation(List<AVSFRecordForSpecificMonth> result);

    Representation generateRepresentation(List<AVSFRecordForSpecificYear> result);

}
