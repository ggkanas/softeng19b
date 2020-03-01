package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month;
    private double dayAheadTotalLoadForecastByDayValue;

    public DayAheadTotalLoadForecastRecordForSpecificMonth() {
        super(DataSet.monthAheadTotalLoadByDay);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getDayAheadTLForecastByDayValue() {
        return dayAheadTLForecastByDayValue;
    }


    public void setDayAheadTLForecastByDayValue(double dayAheadTLForecastByDayValueValue) {
        this.dayAheadTLForecastByDayValue = dayAheadTLForecastByDayValue;
    }
}
