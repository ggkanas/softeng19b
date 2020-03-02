package gr.ntua.ece.softeng19b.data;

import gr.ntua.ece.softeng19b.data.model.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.lang.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Calendar;
import org.springframework.dao.EmptyResultDataAccessException;
import org.restlet.resource.ResourceException;

public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public void accessDataCheck() throws DataAccessException {
        try {
            jdbcTemplate.query("select 1", ResultSet::next);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean isAdmin(String token) {
        Object[] sqlParams = new Object[] {
            token
        };
        try {
            return jdbcTemplate.queryForObject("SELECT Username FROM User WHERE Token = ?", sqlParams, (ResultSet rs, int rowNum) -> {
                return (rs.getString(1) == "admin");
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void createToken(String token, String username) throws DataAccessException {
        try {
            jdbcTemplate.update("UPDATE User SET Token='"+ token +"' WHERE Username='"+ username +"'");
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean checkToken(String token) throws DataAccessException {
        Object[] sqlParams = new Object[] {
            token
        };
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM User WHERE Token = ?", sqlParams, (ResultSet rs, int rowNum) -> {
                return (rs.getInt(1) > 0);
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void destroyToken(String token) throws DataAccessException {
        try {
            jdbcTemplate.update("UPDATE User SET Token=null WHERE Token='"+ token +"'");
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean hasRemaining(String token) {
        Calendar cal = Calendar.getInstance();
        Object[] sqlParams = new Object[] {
            token
        };
        try {
            return jdbcTemplate.queryForObject("SELECT RemainingRequests, Period FROM User WHERE Token = ?", sqlParams, (ResultSet rs, int rowNum) -> {
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                Date newDate = cal.getTime();
                return (rs.getInt(1) != 0 || rs.getTimestamp(2).before(new Timestamp(newDate.getTime())));
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void changeRemaining(String token) {
        Calendar cal = Calendar.getInstance();
        Object[] sqlParams = new Object[] {
            token
        };
        boolean notExpired;
        try {
            notExpired = jdbcTemplate.queryForObject("SELECT Period FROM User WHERE Token = ?", sqlParams, (ResultSet rs, int rowNum) -> {
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                Date newDate = cal.getTime();
                return (rs.getΤimestamp(1).after(new Timestamp(newDate.getTime())));
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        if (notExpired) {
            String sqlQuery = "UPDATE User SET PERIOD=CURRENT_TIMESTAMP(), RemainingRequests=RequestsPerDayQuota-1 WHERE Token = ?";
            try {
                notExpired = jdbcTemplate.update(sqlQuery, sqlParams);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        else {
            String sqlQuery = "UPDATE User SET RemainingRequests=RemainingRequests-1 WHERE Token = ?";
            try {
                notExpired = jdbcTemplate.update(sqlQuery, sqlParams);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
    }

    public int addActualTotalLoadRecord(String[] dataLine) {

                Object[] sqlParams = new Object[] {
                    Integer.parseInt(dataLine[0]),
                    Timestamp.ValueOf(dataLine[1]),
                    Timestamp.ValueOf(dataLine[2]),
                    Long.parseLong(dataLine[3]),
                    dataLine[4],
                    Integer.parseInt(dataLine[5]),
                    Integer.parseInt(dataLine[6]),
                    Integer.parseInt(dataLine[7]),
                    Timestamp.ValueOf(dataLine[8]),
                    dataLine[9],
                    Timestamp.ValueOf(dataLine[10]),
                    new BigDecimal(dataLine[11]),
                    Integer.parseInt(dataLine[12]),
                    Integer.parseInt(dataLine[13]),
                    Integer.parseInt(dataLine[14]),
                    Integer.parseInt(dataLine[15]),
                    dataLine[16]
                };

            String sqlQuery = "insert into ActualTotalLoad () values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                return jdbcTemplate.update(sqlQuery, sqlParams);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }

    public int addAggregatedGenerationPerType(String[] dataLine) {

                Object[] sqlParams = new Object[] {
                    Integer.parseInt(dataLine[0]),
                    Timestamp.ValueOf(dataLine[1]),
                    Timestamp.ValueOf(dataLine[2]),
                    Long.parseLong(dataLine[3]),
                    dataLine[4],
                    Integer.parseInt(dataLine[5]),
                    Integer.parseInt(dataLine[6]),
                    Integer.parseInt(dataLine[7]),
                    Timestamp.ValueOf(dataLine[8]),
                    dataLine[9],
                    Timestamp.ValueOf(dataLine[10]),
                    new BigDecimal(dataLine[11]),
                    new BigDecimal(dataLine[12]),
                    Integer.parseInt(dataLine[13]),
                    Integer.parseInt(dataLine[14]),
                    Integer.parseInt(dataLine[15]),
                    Integer.parseInt(dataLine[16]),
                    Integer.parseInt(dataLine[17]),
                    dataLine[18]
                };

            String sqlQuery = "insert into AggregatedGenerationPerType () values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                return jdbcTemplate.update(sqlQuery, sqlParams);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }

        public int addDayAheadTotalLoadForecast(String[] dataLine) {

                Object[] sqlParams = new Object[] {
                    Integer.parseInt(dataLine[0]),
                    Timestamp.ValueOf(dataLine[1]),
                    Timestamp.ValueOf(dataLine[2]),
                    Long.parseLong(dataLine[3]),
                    dataLine[4],
                    Integer.parseInt(dataLine[5]),
                    Integer.parseInt(dataLine[6]),
                    Integer.parseInt(dataLine[7]),
                    Timestamp.ValueOf(dataLine[8]),
                    dataLine[9],
                    Timestamp.ValueOf(dataLine[10]),
                    new BigDecimal(dataLine[11]),
                    Integer.parseInt(dataLine[12]),
                    Integer.parseInt(dataLine[13]),
                    Integer.parseInt(dataLine[14]),
                    Integer.parseInt(dataLine[15]),
                    dataLine[16]
                };

            String sqlQuery = "insert into DayAheadTotalLoadForecast () values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                return jdbcTemplate.update(sqlQuery, sqlParams);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }

    public int getTotalRecordsInDatabase(String dataset) {
        String sqlQuery = "SELECT COUNT(*) FROM " + dataset;
        try {
            return jdbcTemplate.queryForObject(sqlQuery, (ResultSet rs, int rowNum) -> {
                return rs.getInt(1);
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User getUser(String username) throws DataAccessException, ResourceException {
        sqlQuery = "select * from User where User.Username = ?";

        try {
            return jdbcTemplate.queryForObject(sqlQuery, new Object[] { username }, (ResultSet rs, int rownum) ->
          new User(
          rs.getString(2),
          rs.getString(3),
          rs.getString(4),
          rs.getInt(5),
          rs.getTimestamp(6),
          rs.getInt(7),
          rs.getString(8))
          );
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN);
        } catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateUser(User user) throws DataAccessException {
        try {
            jdbcTemplate.update("UPDATE User SET Password = ?, Email = ?, RequestsPerDayQuota = ? WHERE Username = ? ",
                new Object[] {user.getPassword(), user.getEmail(), user.getRequestsPerDayQuota(), user.getUsername()});
        } catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

    }

    public int addUser(User user) {
        Object[] sqlParams = new Object[] {
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getRequestsPerDayQuota(),
            user.getPeriod(),
            user.getRemainingRequests(),
        };

        String sqlQuery = "insert into User (Username,Email,Password,RequestsPerDayQuota,Period,RemainingRequests) values(?,?,?,?,?,?)";
        try {
            return jdbcTemplate.update(sqlQuery, sqlParams);
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

/* OLD addUser()
    public User addUser(String username, String password, String email, Integer requestsPerDayQuotas) {
            TransactionTemplate transactionTemplate = new TransactionTemplate(tm);
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            long id = transactionTemplate.execute((TransactionStatus status) -> {
                //Create the new user record using a prepared statement
                GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
                int rowCount = jdbcTemplate.update((Connection con) -> {
                    PreparedStatement ps = con.prepareStatement(
                        "insert into user(username, password, email, quotas) values(?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, email);
                    ps.setInteger(4, requestPerDayQuotas); //ελπίζω να υπάρχει αυτό
                    return ps;
                }, keyHolder);
                if (rowCount != 1) {
                    throw new RuntimeException("New user not inserted");
                }
                long newId = keyHolder.getKey().longValue();
                return newId;
            });
            //New row has been added
            User user = new User(
                id,
                username,
                password,
                email,
                requestsPerDayQuotas
            );
            return user;
        }*/

    public List<ATLRecordForSpecificDay> fetchActualTotalLoadForSpecificDate(String areaName, String resolution, LocalDate date)
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

            String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, DateTime, TotalLoadValue, UpdateTime " +
            "from ActualTotalLoad as ATL, MapCode as MC, AreadTypeCode as ATC, " +
            "ResolutionCode as RC where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id && " +
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
                    dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime()));
                    dataLoad.setActualTotalLoadValue(rs.getDouble(9));
                    dataLoad.setUpdateTime(new DateTime(rs.getTimestamp(10).getTime()));
                    return dataLoad;
                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }

    public List<ATLRecordForSpecificMonth> fetchActualTotalLoadForSpecificMonth(String areaName, String resolution, YearMonth yearMonth)

    throws DataAccessException {
      Integer year = yearMonth.getYear();
      Integer month = yearMonth.getMonthValue();

    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      year,
      month
    };

    String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, sum(TotalLoadValue) " +
    "from ActualTotalLoad as ATL, MapCode as MC, AreaTypeCode as ATC, " +
    "ResolutionCode as RC where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id && " +
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

    public List<ATLRecordForSpecificYear> fetchActualTotalLoadForSpecificYear(String areaName, String resolution, Integer year)

    throws DataAccessException {
      //Integer year = date.getYear();

    Object[] sqlParams = new Object[] {
      areaName,
      resolution,
      year
    };

    String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, sum(TotalLoadValue) " +
    "from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC " +
    "where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id && " +
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
}


    public List<AGPerTypeRecordForSpecificDay> fetchAggregatedGenerationPerTypeForSpecificDate(String areaName, String productionType, String resolution, LocalDate date) throws DataAccessException {
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

        String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText, " +
        "Year, Month, Day, DateTime, ProductionTypeText, ActualGenerationOutput, UpdateTime from " +
        "AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where " +
        "AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
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

        String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText, " +
        "Year, Month, Day, DateTime, ProductionTypeText, ActualGenerationOutput, UpdateTime from " +
        "AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where " +
        "AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
        "AreaName = ? && ResolutionCodeText = ? && ProductionTypeText = ? && Year = ? && Month = ? and Day = ?";
      }

      try {
          return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
              AGPerTypeRecordForSpecificDay dataLoad = new AGPerTypeRecordForSpecificDay();
              dataLoad.setAreaName(rs.getString(1));
              dataLoad.setAreaTypeCode(rs.getString(2));
              dataLoad.setMapCode(rs.getString(3));
              dataLoad.setResolutionCode(rs.getString(4));
              dataLoad.setYear(rs.getInt(5));
              dataLoad.setMonth(rs.getInt(6));
              dataLoad.setDay(rs.getInt(7));
              dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime()));
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

    public List<AGPerTypeRecordForSpecificMonth> fetchAggregatedGenerationPerTypeForSpecificMonth(String areaName, String productionType, String resolution, YearMonth yearMonth) throws DataAccessException {
      Integer year = yearMonth.getYear();
      Integer month = yearMonth.getMonthValue();

      if(productionType == "AllTypes") {
        Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year,
          month
        };

        String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText, " +
        "Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from " +
        "AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where " +
        "AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
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

        String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText, " +
        "Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from " +
        "AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where " +
        "AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
        "AreaName = ? && ResolutionCodeText = ? && ProductionTypeText = ? && Year = ? && Month = ? group by Day";
      }

      try {
          return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
              AGPerTypeRecordForSpecificMonth dataLoad = new AGPerTypeRecordForSpecificMonth();
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

    public List<AGPerTypeRecordForSpecificYear> fetchAggregatedGenerationPerTypeForSpecificYear(String areaName, String productionType, String resolution, Integer year) throws DataAccessException {
      //Integer year = date.getYear();

      if(productionType == "AllTypes") {
        Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          year
        };

        String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText, " +
        "Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from " +
        "AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where " +
        "AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
        "AreaName = ? && ResolutionCodeText = ? && Year = ? group by ProductionTypeText, Month";
      }
      else {
        Object[] sqlParams = new Object[] {
          areaName,
          resolution,
          productionType,
          year
        };

        String sqlQuery = "AreaName, AreaTypeCodetext, MapCodeText, ResolutionCodeText, " +
        "Year, Month, Day, ProductionTypeText, sum(ActualGenerationOutput) from " +
        "AggregatedGenerationPerType as AGPT, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, ProductionType as PT where " +
        "AGPT.AreaTypeCodeId = ATC.Id && AGPT.ProductionTypeId = PC.Id && AGPT.ResolutionCodeId = RC.Id && AGPT.MapCodeId = MC.Id && " +
        "AreaName = ? && ResolutionCodeText = ? && ProductionTypeText = ? && Year = ? group by Month";
      }

      try {
          return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
              AGPerTypeRecordForSpecificYear dataLoad = new AGPerTypeRecordForSpecificYear();
              dataLoad.setAreaName(rs.getString(1));
              dataLoad.setAreaTypeCode(rs.getString(2));
              dataLoad.setMapCode(rs.getString(3));
              dataLoad.setResolutionCode(rs.getString(4));
              dataLoad.setYear(rs.getInt(5));
              dataLoad.setMonth(rs.getInt(6));
              dataLoad.setDay(rs.getInt(7));
              dataLoad.setProductionType(rs.getString(8));
              dataLoad.setActualGenerationOutputByMonthValue(rs.getDouble(9));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public List<DayAheadTLForecastRecordForSpecificDay> fetchDayAheadTotalLoadForecastForSpecificDate(String areaName, String resolution, LocalDate date)

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

      String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, " +
      "Month, Day, DateTime, TotalLoadValue, UpdateTime from " +
      "DayAheadTotalLoadForecast as DATLF, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC where " +
      "DATLF.AreaTypeCodeId = ATC.Id && DATLF.MapCodeId = MC.Id && DATLF.ResolutionCodeId = RC.Id && " +
      "AreaName = ? && ResolutionCodeText = ? Year = ? && Month = ? && Day = ?";

      try {
          return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
              DayAheadTLForecastRecordForSpecificDay dataLoad = new DayAheadTLForecastRecordForSpecificDay();
              dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
              dataLoad.setAreaTypeCode(rs.getString(2));
              dataLoad.setMapCode(rs.getString(3));
              dataLoad.setResolutionCode(rs.getString(4));
              dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
              dataLoad.setMonth(rs.getInt(6));
              dataLoad.setDay(rs.getInt(7));
              dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime()));
              dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(9));
              dataLoad.setUpdateTime(new DateTime(rs.getTimestamp(10).getTime()));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public List<DayAheadTLForecastRecordForSpecificMonth> fetchDayAheadTotalLoadForecastForSpecificMonth(String areaName, String resolution, YearMonth yearMonth)

    throws DataAccessException {
      Integer year = yearMonth.getYear();
      Integer month = yearMonth.getMonthValue();

      Object[] sqlParams = new Object[] {
              areaName,
              resolution,
              year,
              month
      };

      String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, " +
      "Month, Day, sum(TotalLoadValue) from " +
      "DayAheadTotalLoadForecast as DATLF, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC where " +
      "DATLF.AreaTypeCodeId = ATC.Id && DATLF.MapCodeId = MC.Id && DATLF.ResolutionCodeId = RC.Id && " +
      "AreaName = ? && ResolutionCodeText = ? Year = ? && Month = ? group by Day";

      try {
          return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
              DayAheadTLForecastRecordForSpecificMonth dataLoad = new DayAheadTLForecastRecordForSpecificMonth();
              dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
              dataLoad.setAreaTypeCode(rs.getString(2));
              dataLoad.setMapCode(rs.getString(3));
              dataLoad.setResolutionCode(rs.getString(4));
              dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
              dataLoad.setMonth(rs.getInt(6));
              dataLoad.setDay(rs.getInt(7));
              dataLoad.setDayAheadTotalLoadForecastByDayValue(rs.getDouble(8));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public List<DayAheadTLForecastRecordForSpecificYear> fetchDayAheadTotalLoadForecastForSpecificYear(String areaName, String resolution, Integer year)

    throws DataAccessException {
      //Integer year = date.getYear();

      Object[] sqlParams = new Object[] {
              areaName,
              resolution,
              year
      };

      String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, " +
      "Month, Day, sum(TotalLoadValue) from " +
      "DayAheadTotalLoadForecast as DATLF, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC where " +
      "DATLF.AreaTypeCodeId = ATC.Id && DATLF.MapCodeId = MC.Id && DATLF.ResolutionCodeId = RC.Id && " +
      "AreaName = ? && ResolutionCodeText = ? Year = ? group by Month";

      try {
          return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
              DayAheadTLForecastRecordForSpecificYear dataLoad = new DayAheadTLForecastRecordForSpecificYear();
              dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
              dataLoad.setAreaTypeCode(rs.getString(2));
              dataLoad.setMapCode(rs.getString(3));
              dataLoad.setResolutionCode(rs.getString(4));
              dataLoad.setYear(rs.getInt(5)); //get the int located at the 2nd column of the result set
              dataLoad.setMonth(rs.getInt(6));
              dataLoad.setDay(rs.getInt(7));
              dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getDouble(8));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public List<AVSFRecordForSpecificDay> fetchActualTotalLoadVsDayAheadTotalLoadForecastForSpecificDate(String areaName, String resolution, LocalDate date)

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

      String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, DateTime, ATL.TotalLoadValue, DATLF.TotalLoadValue " +
      "from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, DayAheadTotalLoadForecast as DATLF where" +
      "ATL.AreaTypeCodeId = ATC.Id && DATLF.AreaTypeCodeId = ATC.Id && ATL.MapCodeId = MC.Id && DATLF.MapCodeId = MC.Id && ATL.ResolutionCodeId = RC.Id && " +
      "DATLF.ResolutionCodeId = RC.Id && ATL.AreaName = ? && ATL.ResolutionCodeText = ? && ATL.Year = ? && ATL.Month = ? and ATL.Day = ? && " +
      "ATL.AreaName = DATLF.AreaName && ATL.ResolutionCodeText = DATLF.ResolutionCodeText && ATL.Year = DATLF.Year && ATL.Month = DATLF.Month and ATL.Day = DATLF.Day";

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
              dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime()));
              dataLoad.setActualTotalLoadValue(rs.getDouble(9));
              dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(10));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public List<AVSFRecordForSpecificMonth> fetchActualTotalLoadVsDayAheadTotalLoadForecastForSpecificMonth(String areaName, String resolution, YearMonth yearMonth) throws DataAccessException {
      Integer year = yearMonth.getYear();
      Integer month = yearMonth.getMonthValue();

      Object[] sqlParams = new Object[] {
              areaName,
              resolution,
              year,
              month
      };

      String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, sum(ATL.TotalLoadValue), sum(DATLF.TotalLoadValue) " +
      "from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, DayAheadTotalLoadForecast as DATLF where" +
      "ATL.AreaTypeCodeId = ATC.Id && DATLF.AreaTypeCodeId = ATC.Id && ATL.MapCodeId = MC.Id && DATLF.MapCodeId = MC.Id && ATL.ResolutionCodeId = RC.Id && " +
      "DATLF.ResolutionCodeId = RC.Id && ATL.AreaName = ? && ATL.ResolutionCodeText = ? && ATL.Year = ? && ATL.Month = ?  && " +
      "ATL.AreaName = DATLF.AreaName && ATL.ResolutionCodeText = DATLF.ResolutionCodeText && ATL.Year = DATLF.Year && ATL.Month = DATLF.Month group by Day";

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
              dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime()));
              dataLoad.setActualTotalLoadByDayValue(rs.getDouble(9));
              dataLoad.setDayAheadTotalLoadForecastByDayValue(rs.getDouble(10));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public List<AVSFRecordForSpecificYear> fetchActualTotalLoadVsDayAheadTotalLoadForecastForSpecificYear(String areaName, String resolution, Integer year)

    throws DataAccessException {
      //Integer year = date.getYear();

      Object[] sqlParams = new Object[] {
              areaName,
              resolution,
              year
      };

      String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, sum(ATL.TotalLoadValue), sum(DATLF.TotalLoadValue) " +
      "from ActualTotalLoad as ATL, AreaTypeCode as ATC, MapCode as MC, ResolutionCode as RC, DayAheadTotalLoadForecast as DATLF where" +
      "ATL.AreaTypeCodeId = ATC.Id && DATLF.AreaTypeCodeId = ATC.Id && ATL.MapCodeId = MC.Id && DATLF.MapCodeId = MC.Id && ATL.ResolutionCodeId = RC.Id && " +
      "DATLF.ResolutionCodeId = RC.Id && ATL.AreaName = ? && ATL.ResolutionCodeText = ? && ATL.Year = ? && " +
      "ATL.AreaName = DATLF.AreaName && ATL.ResolutionCodeText = DATLF.ResolutionCodeText && ATL.Year = DATLF.Year group by Month";

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
              dataLoad.setDateTime(new DateTime(rs.getTimestamp(8).getTime()));
              dataLoad.setActualTotalLoadByMonthValue(rs.getDouble(9));
              dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getDouble(10));
              return dataLoad;
          });
      }
      catch(Exception e) {
          throw new DataAccessException(e.getMessage(), e);
      }
    }

    public int deleteATL() {
        try {
            return jdbcTemplate.update("DELETE FROM ActualTotalLoad", (ResultSet rs, int rowNum) -> {
                return rowNum;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public int deleteAGPT() {
        try {
            return jdbcTemplate.update("DELETE FROM AggregatedGenerationPerType", (ResultSet rs, int rowNum) -> {
                return rowNum;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public int deleteDATLF() {
        try {
            return jdbcTemplate.update("DELETE FROM DayAheadTotalLoadForecast", (ResultSet rs, int rowNum) -> {
                return rowNum;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public int deleteUsers() {
        try {
            return jdbcTemplate.update("DELETE FROM User WHERE Username != 'admin'", (ResultSet rs, int rowNum) -> {
                return rowNum;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
