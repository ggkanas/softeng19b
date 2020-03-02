package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month;
    private double dayAheadTotalLoadForecastByDayValue;

    public DayAheadTLForecastRecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getDayAheadTLForecastByDayValue() {
        return dayAheadTotalLoadForecastByDayValue;
    }


    public void setDayAheadTLForecastByDayValue(double dayAheadTLForecastByDayValueValue) {
        this.dayAheadTotalLoadForecastByDayValue = dayAheadTotalLoadForecastByDayValue;
    }
}
