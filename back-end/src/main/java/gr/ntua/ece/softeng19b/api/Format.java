package gr.ntua.ece.softeng19b.api;

import com.google.gson.stream.JsonWriter;
import com.csvreader.CsvWriter;
import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import gr.ntua.ece.softeng19b.data.model.*;
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

        public Representation generateRepresentation1a(List<ATLRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(ATLRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime().toString());
                w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                w.name("UpdateTime").value(rec.getUpdateTime().toString());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation1b(List<ATLRecordForSpecificMonth> result) {
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

public Representation generateRepresentation1c(List<ATLRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(ATLRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value(rec.getSource());
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
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

public Representation generateRepresentation2a(List<AGPerTypeRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPerTypeRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime().toString());
                w.name("ProductionType").value(rec.getProductionType());
                w.name("ActualGenerationOutputValue").value(rec.getActualGenerationOutputValue());
                w.name("UpdateTimeUTC").value(rec.getUpdateTime().toString());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation2b(List<AGPerTypeRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPerTypeRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
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

public Representation generateRepresentation2c(List<AGPerTypeRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPerTypeRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
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

public Representation generateRepresentation3a(List<DayAheadTLForecastRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DayAheadTLForecastRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime().toString());
                w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                w.name("UpdateTime").value(rec.getUpdateTime().toString());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation3b(List<DayAheadTLForecastRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DayAheadTLForecastRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTLForecastByDayValue());
                w.endObject(); // }
                w.flush();
            }
            w.endArray(); // ]
        } catch (IOException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
    });
}

public Representation generateRepresentation3c(List<DayAheadTLForecastRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DayAheadTLForecastRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
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

public Representation generateRepresentation4a(List<AVSFRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AVSFRecordForSpecificDay rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
                w.name("Day").value(rec.getDay());
                w.name("DateTimeUTC").value(rec.getDateTime().toString());
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

public Representation generateRepresentation4b(List<AVSFRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AVSFRecordForSpecificMonth rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
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

public Representation generateRepresentation4c(List<AVSFRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AVSFRecordForSpecificYear rec: result) {
                w.beginObject(); // {
                w.name("Source").value("entso-e");
                w.name("DataSet").value(rec.getDataSet());
                w.name("AreaName").value(rec.getAreaName());
                w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                w.name("MapCode").value(rec.getMapCode());
                w.name("ResolutionCode").value(rec.getResolutionCode());
                w.name("Year").value(rec.getYear());
                w.name("Month").value(rec.getMonth());
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
        public Representation generateRepresentation1a(List<ATLRecordForSpecificDay> result) {
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
                                (new Integer(rec.getYear())).toString(),
                                (new Integer(rec.getMonth())).toString(),
                                (new Integer(rec.getDay())).toString(),
                                rec.getDateTime().toString(),
                                (new Double(rec.getActualTotalLoadValue())).toString(),
                                rec.getUpdateTime().toString()
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

public Representation generateRepresentation1b(List<ATLRecordForSpecificMonth> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","ActualTotalLoadByDayValue"};
            w.writeRecord(headers);

                for(ATLRecordForSpecificMonth rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        (new Double(rec.getActualTotalLoadByDayValue())).toString()
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

public Representation generateRepresentation1c(List<ATLRecordForSpecificYear> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","ActualTotalLoadByMonthValue"};
            w.writeRecord(headers);

                for(ATLRecordForSpecificYear rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Double(rec.getActualTotalLoadByMonthValue())).toString()
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

public Representation generateRepresentation2a(List<AGPerTypeRecordForSpecificDay> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","DateTime","ProductionType", "ActualGenerationOutputValue","UpdateTime"};
            w.writeRecord(headers);

                for(AGPerTypeRecordForSpecificDay rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        rec.getDateTime().toString(),
                        rec.getProductionType(),
                        (new Double(rec.getActualGenerationOutputValue())).toString(),
                        rec.getUpdateTime().toString()
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

public Representation generateRepresentation2b(List<AGPerTypeRecordForSpecificMonth> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","ProductionType", "ActualGenerationOutputByDayValue"};
            w.writeRecord(headers);

                for(AGPerTypeRecordForSpecificMonth rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        rec.getProductionType(),
                        (new Double(rec.getActualGenerationOutputByDayValue())).toString()
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

public Representation generateRepresentation2c(List<AGPerTypeRecordForSpecificYear> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","ProductionType", "ActualGenerationOutputByMonthValue"};
            w.writeRecord(headers);

                for(AGPerTypeRecordForSpecificYear rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        rec.getProductionType(),
                        (new Double(rec.getActualGenerationOutputByMonthValue())).toString()
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

public Representation generateRepresentation3a(List<DayAheadTLForecastRecordForSpecificDay> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","DateTime","DayAheadTotalLoadForecastValue","UpdateTime"};
            w.writeRecord(headers);

                for(DayAheadTLForecastRecordForSpecificDay rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        rec.getDateTime().toString(),
                        (new Double(rec.getDayAheadTotalLoadForecastValue())).toString(),
                        rec.getUpdateTime().toString()
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

public Representation generateRepresentation3b(List<DayAheadTLForecastRecordForSpecificMonth> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","DayAheadTotalLoadForecastByDayValue"};
            w.writeRecord(headers);

                for(DayAheadTLForecastRecordForSpecificMonth rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        (new Double(rec.getDayAheadTLForecastByDayValue())).toString()
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

public Representation generateRepresentation3c(List<DayAheadTLForecastRecordForSpecificYear> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","DayAheadTotalLoadForecastByMonthValue"};
            w.writeRecord(headers);

                for(DayAheadTLForecastRecordForSpecificYear rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Double(rec.getDayAheadTotalLoadForecastByMonthValue())).toString()
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

public Representation generateRepresentation4a(List<AVSFRecordForSpecificDay> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","DateTime","DayAheadTotalLoadForecastValue","ActualTotalLoadValue"};
            w.writeRecord(headers);

                for(AVSFRecordForSpecificDay rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        rec.getDateTime().toString(),
                        (new Double(rec.getDayAheadTotalLoadForecastValue())).toString(),
                        (new Double(rec.getActualTotalLoadValue())).toString()
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

public Representation generateRepresentation4b(List<AVSFRecordForSpecificMonth> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","Day","DayAheadTotalLoadForecastByDayValue","ActualTotalLoadByDayValue"};
            w.writeRecord(headers);

                for(AVSFRecordForSpecificMonth rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Integer(rec.getDay())).toString(),
                        (new Double(rec.getDayAheadTotalLoadForecastByDayValue())).toString(),
                        (new Double(rec.getActualTotalLoadByDayValue())).toString()
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

public Representation generateRepresentation4c(List<AVSFRecordForSpecificYear> result) {
    return new CustomCsvRepresentation( (CsvWriter w) -> {
        try {
            StringBuilder sb = new StringBuilder();
            String[] headers = {"Source","DataSet","AreaName","AreaTypeCode","MapCode","ResolutionCode",
                "Year","Month","DayAheadTotalLoadForecastByMonthValue","ActualTotalLoadByMonthValue"};
            w.writeRecord(headers);

                for(AVSFRecordForSpecificYear rec: result) {
                    String[] values = {
                        rec.getSource(),
                        rec.getDataSet(),
                        rec.getAreaName(),
                        rec.getAreaTypeCode(),
                        rec.getMapCode(),
                        rec.getResolutionCode(),
                        (new Integer(rec.getYear())).toString(),
                        (new Integer(rec.getMonth())).toString(),
                        (new Double(rec.getDayAheadTotalLoadForecastByMonthValue())).toString(),
                        (new Double(rec.getActualTotalLoadByMonthValue())).toString()
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
