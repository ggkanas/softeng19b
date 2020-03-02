package gr.ntua.ece.softeng19b.api;

import com.google.gson.stream.JsonWriter;
import com.csvreader.CsvWriter;
import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

public enum Format implements RepresentationGenerator {
    JSON {

        public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(ATLRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime());
                w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                w.name("UpdateTime").value(rec.getUpdateTime());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<ATLRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(ATLRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value(rec.getSource());
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadByDayValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(ATLRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value(rec.getSource());
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadByMonthValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<AGPerTypeRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPerTypeRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime());
                w.name("ProductionType").value(rec.getProductionType());
                w.name("ActualGenerationOutputValue").value(rec.getActualGenerationOutputValue());
                w.name("UpdateTimeUTC").value(rec.getUpdateTime());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<AGPerTypeRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPerTypeRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("ProductionType").value(rec.getProductionType());
                w.name("ActualGenerationOutputByDayValue").value(rec.getActualGenerationOutputByDayValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<AGPerTypeRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPerTypeRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("ProductionType").value(rec.getProductionType());
                w.name("ActualGenerationOutputByMonthValue").value(rec.getActualGenerationOutputByMonthValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<DayAheadTLFRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DayAheadTLFRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime());
                w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                w.name("UpdateTime").value(rec.getUpdateTime());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<DayAheadTLFRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DayAheadTLFRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastByDayValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<DayAheadTLFRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DayAheadTLFRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastByMonthValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<AVSFRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AVSFRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime());
                w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<AVSFRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AVSFRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastByDayValue());
                w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadByDayValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation(List<AVSFRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AVSFRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value("ActualTotalLoad");
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastByMonthValue());
                w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadByMonthValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

    },
    CSV {
        /*** ??? ***/
        public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
            return new CustomCsvRepresentation( (CsvWriter w) -> {
                try {
                    StringBuilder sb = new StringBuilder();
                    String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                        "Year","Month","Day","DateTime","ActualTotalLoadValue","UpdateTime"};
                    w.writeRecord(headers);

                        for(ATLRecordForSpecificDay rec: result) {
                            String[] values = {
                                rec.getSource(),
                                rec.getDataSet(),
                                rec.getAreaName(),
                                rec.getAreaTypeCode(),
                                rec.getMapCode(),
                                rec.getResolutionCode(),
                                rec.getYear(),
                                rec.getMonth(),
                                rec.getDay(),
                                rec.getDateTime(),
                                rec.getActualTotalLoadValue(),
                                rec.getUpdateTime()
                            };
                            w.writeRecord(values);
                            w.flush();
                            //w.endRecord(); may or may not be needed here
                        }
                    } catch (IOException e) {
                        throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                    }
                });
            }


        }
    };

    final class CustomJsonRepresentation extends WriterRepresentation {

        private final Consumer<JsonWriter> consumer;

        CustomJsonRepresentation(Consumer<JsonWriter> consumer) {
            super(MediaType.APPLICATION_JSON);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            JsonWriter jsonWriter = new JsonWriter(writer);
            consumer.accept(jsonWriter);
        }
    }

    final class CustomCsvRepresentation extends WriterRepresentation {

        private final Consumer<CsvWriter> consumer;

        CustomCsvRepresentation(Consumer<CsvWriter> consumer) {
            super(MediaType.TEXT_PLAIN);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            CsvWriter csvWriter = new CsvWriter(writer, ',');
            consumer.accept(csvWriter);
        }
    }
