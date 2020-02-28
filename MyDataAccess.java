public List<ATLRecordForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName, String resolution, LocalDate date)
            throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, DateTime, TotalLoadValue, UpdateTime
        from ActualTotalLoad as ATL, MapCode as MC, AreadTypeCode as ATC,
        ResolutionCode as RC where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id && " +
                          "AreaName = ? && ResolutionCodeText = ? && Year = ? && Month = ? && Day = ?";

        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ATLRecordForSpecificDay dataLoad = new ATLRecordForSpecificDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime());
                dataLoad.setActualTotalLoadValue(rs.getDouble(9));
                dataLoad.setUpdateTime(new DateTime(rs.getTimestamp(10).getTime()));
                return dataLoad;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

public List<ATLRecordForSpecificMonth> fetchActualDataLoadForSpecificMonth(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  Integer month = date.getMonthValue();

Object[] sqlParams = new Object[] {
  areaName,
  resolution,
  year,
  month
};

String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, sum(TotalLoadValue)
from ActualTotalLoad as ATL, MapCode as MC, AreaTypeCode as ATC,
ResolutionCode as RC where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id && " +
"AreaName = ? && ResolutionCodeText = ? && Year = ? && Month = ? group by Day";


try {
  return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
    ATLRecordForSpecificMonth dataLoad = new ATLRecordForSpecificMonth();
    dataLoad.setAreaName(rs.getString(1));
    dataLoad.setAreaTypeCode(rs.getString(2));
    dataLoad.setMapCode(rs.getString(3));
    dataLoad.setResolutionCode(rs.getString(4));
    dataLoad.setYear(rs.getInt(5));
    dataLoad.setMonth(rs.getInt(6));
    dataLoad.setDay(rs.getInt(7));
    dataLoad.setActualTotalLoadValueByDayValue(rs.getDouble(9));
    return dataLoad;
  });
}
catch(Exception e) {
  throw new DataAccessException(e.getMessage(), e);
}
}

public List<ATLRecordForSpecificYear> fetchActualDataLoadForSpecificYear(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();

Object[] sqlParams = new Object[] {
  areaName,
  resolution,
  year
};

String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, sum(TotalLoadValue)
from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC
where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id &&" +
"AreaName = ? && ResolutionCodetext = ? && Year = ? + group by Month";

try {
  return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
    ATLRecordForSpecificYear dataLoad = new ATLRecordForSpecificYear();
    dataLoad.setAreaName(rs.getString(1));
    dataLoad.setAreaTypeCode(rs.getString(2));
    dataLoad.setMapCode(rs.getString(3));
    dataLoad.setResolutionCode(rs.getString(4));
    dataLoad.setYear(rs.getInt(5));
    dataLoad.setMonth(rs.getInt(6));
    dataLoad.setActualTotalLoadValueByMonthValue(rs.getDouble(7));
    return dataLoad;
  });
}
catch(Exception e) {
  throw new DataAccessException(e.getMessage(), e);
}


public List<AGPTRecordForSpecificDay> fetchAggregatedGenerationPerTypeForSpecificDate(String areaName, String productionType, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  Integer month = date.getMonthValue();
  Integer day = date.getDayOfMonth();

  if(productionType == "AllTypes") {
    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      year,
      month,
      day
    };

    String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText,
    Year, Month, Day, DateTime, ProductionTypeText, ActualGenerationOutput, UpdateTime from
    AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where
    AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
    "AreaName = ? && ResolutionCodeText = ? && Year = ? && Month = ? and Day = ? group by ProductionTypeText";
  }
  else {
    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      productionType,
      year,
      month,
      day
    };

    String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText,
    Year, Month, Day, DateTime, ProductionTypeText, ActualGenerationOutput, UpdateTime from
    AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where
    AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
    "AreaName = ? && ResolutionCodeText = ? && ProductionTypeText = ? && Year = ? && Month = ? and Day = ?";
  }

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          AGPTRecordForSpecificDay dataLoad = new AGPTRecordForSpecificDay();
          dataLoad.setAreaName(rs.getString(1));
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5));
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime());
          dataLoad.setProductionType(rs.getString(9));
          dataLoad.setActualGenerationOutputValue(rs.getDouble(10));
          dataLoad.setUpdateTime(new DateTime(rs.getTimestamp(11).getTime()));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<AGPTRecordForSpecificMonth> fetchAggregatedGenerationPerTypeForSpecificMonth(String areaName, String productionType, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  Integer month = date.getMonthValue();

  if(productionType == "AllTypes") {
    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      year,
      month
    };

    String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText,
    Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from
    AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where
    AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
    "AreaName = ? && ResolutionCodeText = ? && Year = ? && Month = ? group by ProductionTypeText, Day";
  }
  else {
    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      productionType,
      year,
      month
    };

    String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText,
    Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from
    AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where
    AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
    "AreaName = ? && ResolutionCodeText = ? && ProductionTypeText = ? && Year = ? && Month = ? group by Day";
  }

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          AGPTRecordForSpecificMonth dataLoad = new AGPTRecordForSpecificMonth();
          dataLoad.setAreaName(rs.getString(1));
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5));
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setProductionType(rs.getString(8));
          dataLoad.setActualGenerationOutputByDayValue(rs.getDouble(9));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<AGPTRecordForSpecificYear> fetchAggregatedGenerationPerTypeForSpecificYear(String areaName, String productionType, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();

  if(productionType == "AllTypes") {
    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      year
    };

    String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText,
    Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from
    AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where
    AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
    "AreaName = ? && ResolutionCodeText = ? && Year = ? group by ProductionTypeText, Month";
  }
  else {
    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      productionType,
      year
    };

    String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText,
    Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from
    AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where
    AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
    "AreaName = ? && ResolutionCodeText = ? && ProductionTypeText = ? && Year = ? group by Month";
  }

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          AGPTRecordForSpecificYear dataLoad = new AGPTRecordForSpecificYear();
          dataLoad.setAreaName(rs.getString(1));
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5));
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setProductionType(rs.getString(8));
          dataLoad.setActualGenerationOutputByDayValue(rs.getDouble(9));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<DATLFRecordForSpecificDay> fetchDayAheadTotalLoadForecastRecordForSpecificDate(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  Integer month = date.getMonthValue();
  Integer day = date.getDayOfMonth();

  Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year,
          month,
          day
  };

  String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year,
  Month, Day, DateTime, TotalLoadValue, UpdateTime from
  DayAheadTotalLoadForecast as DATLF, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC where
  DATLF.AreaTypeCodeId == ATC.Id && DATLF.MapCodeId = MC.Id && DATLF.ResolutionCodeId = RC.Id &&
  AreaName = ? && ResolutionCodeText = ? Year = ? && Month = ? && Day = ?";

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          DATLFRecordForSpecificDay dataLoad = new DATLFRecordForSpecificDay();
          dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime());
          dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(9));
          dataLoad.setUpdateTime(new DateTime(rs.getTimestamp(10).getTime()));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<DATLFRecordForSpecificMonth> fetchDayAheadTotalLoadForecastRecordForSpecificMonth(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  Integer month = date.getMonthValue();

  Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year,
          month
  };

  String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year,
  Month, Day, sum(TotalLoadValue) from
  DayAheadTotalLoadForecast as DATLF, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC where
  DATLF.AreaTypeCodeId == ATC.Id && DATLF.MapCodeId = MC.Id && DATLF.ResolutionCodeId = RC.Id &&
  AreaName = ? && ResolutionCodeText = ? Year = ? && Month = ? group by Day";

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          DATLFRecordForSpecificMonth dataLoad = new DATLFRecordForSpecificMonth();
          dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<DATLFRecordForSpecificYear> fetchDayAheadTotalLoadForecastRecordForSpecificYear(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();

  Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year
  };

  String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year,
  Month, Day, sum(TotalLoadValue) from
  DayAheadTotalLoadForecast as DATLF, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC where
  DATLF.AreaTypeCodeId == ATC.Id && DATLF.MapCodeId = MC.Id && DATLF.ResolutionCodeId = RC.Id &&
  AreaName = ? && ResolutionCodeText = ? Year = ? group by Month";

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          DATLFRecordForSpecificYear dataLoad = new DATLFRecordForSpecificYear();
          dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<AVSFRecordForSpecificDay> fetchActualVsForecastRecordForSpecificDate(String areaName, String resolution, LocalDate date)

throws DataAccessException {

  Integer year = date.getYear();
  Integer month = date.getMonthValue();
  Integer day = date.getDayOfMonth();

  Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year,
          month,
          day
  };

  String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, DateTime, ATL.TotalLoadValue, DATLF.TotalLoadValue
  from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, DayAheadTotalLoadForecast as DATLF where" +
  "ATL.AreaTypeCodeId = ATC.Id && DATLF.AreaTypeCodeId = ATC.Id && ATL.MapCodeId = MC.Id && DATLF.MapCodeId = MC.Id && ATL.ResolutionCodeId = RC.Id &&
  DATLF.ResolutionCodeId = RC.Id && ATL.AreaName = ? && ATL.ResolutionCodeText = ? && ATL.Year = ? && ATL.Month = ? and ATL.Day = ? &&
  ATL.AreaName = DATLF.AreaName && ATL.ResolutionCodeText = DATLF.ResolutionCodeText && ATL.Year = DATLF.Year && ATL.Month = DATLF.Month and ATL.Day = DATLF.Day?"

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          AVSFRecordForSpecificDay dataLoad = new AVSFRecordForSpecificDay();
          dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime());
          dataLoad.setActualTotalLoadValue(rs.getDouble(9));
          dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(10));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<AVSFRecordForSpecificMonth> fetchActualVsForecastRecordForSpecificMonth(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  Integer month = date.getMonthValue();

  Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year,
          month
  };

  String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, sum(ATL.TotalLoadValue), sum(DATLF.TotalLoadValue)
  from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, DayAheadTotalLoadForecast as DATLF where" +
  "ATL.AreaTypeCodeId = ATC.Id && DATLF.AreaTypeCodeId = ATC.Id && ATL.MapCodeId = MC.Id && DATLF.MapCodeId = MC.Id && ATL.ResolutionCodeId = RC.Id &&
  DATLF.ResolutionCodeId = RC.Id && ATL.AreaName = ? && ATL.ResolutionCodeText = ? && ATL.Year = ? && ATL.Month = ?  &&
  ATL.AreaName = DATLF.AreaName && ATL.ResolutionCodeText = DATLF.ResolutionCodeText && ATL.Year = DATLF.Year && ATL.Month = DATLF.Month group by Day"

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          AVSFRecordForSpecificMonth dataLoad = new AVSFRecordForSpecificMonth();
          dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime());
          dataLoad.setActualTotalLoadByMonthValue(rs.getDouble(9));
          dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getDouble(10));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}

public List<AVSFRecordForSpecificYear> fetchActualVsForecastRecordForSpecificYear(String areaName, String resolution, LocalDate date)

throws DataAccessException {
  Integer year = date.getYear();
  
  Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year
  };

  String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, sum(ATL.TotalLoadValue), sum(DATLF.TotalLoadValue)
  from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, DayAheadTotalLoadForecast as DATLF where" +
  "ATL.AreaTypeCodeId = ATC.Id && DATLF.AreaTypeCodeId = ATC.Id && ATL.MapCodeId = MC.Id && DATLF.MapCodeId = MC.Id && ATL.ResolutionCodeId = RC.Id &&
  DATLF.ResolutionCodeId = RC.Id && ATL.AreaName = ? && ATL.ResolutionCodeText = ? && ATL.Year = ? &&
  ATL.AreaName = DATLF.AreaName && ATL.ResolutionCodeText = DATLF.ResolutionCodeText && ATL.Year = DATLF.Year group by Month"

  try {
      return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
          AVSFRecordForSpecificYear dataLoad = new AVSFRecordForSpecificYear();
          dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
          dataLoad.setAreaTypeCode(rs.getString(2));
          dataLoad.setMapCode(rs.getString(3));
          dataLoad.setResolutionCode(rs.getString(4));
          dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
          dataLoad.setMonth(rs.getInt(6));
          dataLoad.setDay(rs.getInt(7));
          dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime());
          dataLoad.setActualTotalLoadByMonthValue(rs.getDouble(9));
          dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getDouble(10));
          return dataLoad;
      });
  }
  catch(Exception e) {
      throw new DataAccessException(e.getMessage(), e);
  }
}
