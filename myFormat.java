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
                w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadByDayValue())
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

public Representation generateRepresentation(List<AGPTRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPTRecordForSpecificDay rec: result) {
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

public Representation generateRepresentation(List<AGPTRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPTRecordForSpecificMonth rec: result) {
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

public Representation generateRepresentation(List<AGPTRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(AGPTRecordForSpecificYear rec: result) {
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

public Representation generateRepresentation(List<DATLFRecordForSpecificDay> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DATLFRecordForSpecificDay rec: result) {
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

public Representation generateRepresentation(List<DATLFRecordForSpecificMonth> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DATLFRecordForSpecificMonth rec: result) {
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

public Representation generateRepresentation(List<DATLFRecordForSpecificYear> result) {
    return new CustomJsonRepresentation( (JsonWriter w) -> {
        try {
            w.beginArray(); // [
            for(DATLFRecordForSpecificYear rec: result) {
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
