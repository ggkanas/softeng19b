package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class DayAheadTLForecastRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private Timestamp dateTime;
    private double dayAheadTotalLoadForecastValue;
    private Timestamp updateTime;

    public DayAheadTLForecastRecordForSpecificDay() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDayAheadTotalLoadForecastValue() {
        return dayAheadTotalLoadForecastValue;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }
}
