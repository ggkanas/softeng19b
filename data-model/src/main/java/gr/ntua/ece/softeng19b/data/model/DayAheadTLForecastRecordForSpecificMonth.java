package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastByDayValue;

    public DayAheadTLForecastRecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDayAheadTLForecastByDayValue() {
        return dayAheadTotalLoadForecastByDayValue;
    }


    public void setDayAheadTLForecastByDayValue(double dayAheadTLForecastByDayValueValue) {
        this.dayAheadTotalLoadForecastByDayValue = dayAheadTotalLoadForecastByDayValue;
    }
}
