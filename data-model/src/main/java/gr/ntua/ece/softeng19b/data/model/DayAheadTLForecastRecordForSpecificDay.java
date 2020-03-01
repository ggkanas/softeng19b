package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private DateTime dateTime;
    private double dayAheadTotalLoadValue;
    private DateTime UpdateTime;

    public DayAheadTotalLoadForecastRecordForSpecificDay() {
        super(DataSet.DayAheadTotalLoad);
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
        return UpdateTime;
    }

    public void setUpdateTime(DateTime UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public void setDayAheadTotalLoadForecastValue(double DayAheadTotalLoadForecastValue) {
        this.DayAheadTotalLoadForecastValue = DayAheadTotalLoadForecastValue;
    }
}
