package gr.ntua.ece.softeng19b.data;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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


    public List<ATLRecordForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName,
                                                                                        String resolution,
                                                                                        LocalDate date)
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

        //TODO: Insert a valid SQL query
        String sqlQuery = "select AreaName, AreaTypeCodeText, MapCodeText, ResolutionCodeText, Year, Month, Day, DateTime, TotalLoadValue, UpdateTime
        from ActualTotalLoad as ATL, MapCode as MC, AreadTypeCode as ATC,
        ResolutionCode as RC where ATL.MapCodeId = MC.Id && ATL.AreaTypeCodeId = ATC.Id && ATL.ResolutionCodeId = RC.Id" +
                          "AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ?";

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

}
