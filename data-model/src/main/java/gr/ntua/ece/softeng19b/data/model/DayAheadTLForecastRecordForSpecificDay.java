package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class DayAheadTotalLoadForecastRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private Timestamp dateTime; 
    private double dayAheadTotalLoadValue;
    private Timestamp updateTime;

    public DayAheadTotalLoadForecastRecordForSpecificDay() {
        super(DataSet.dayAheadTotalLoad);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDayAheadTotalLoadForecastValue() {
        return actualTotalLoadForecastValue;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(DateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }
}
