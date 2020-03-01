package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month;
    private double monthAheadTotalLoadForecastByDayValue;

    public DayAheadTotalLoadForecastRecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoad);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getDayAheadTLForecastByDayValue() {
        return DayAheadTLForecastByDayValue;
    }


    public void setDayAheadTLForecastByDayValue(double DayAheadTLForecastByDayValueValue) {
        this.DayAheadTLForecastByDayValue = DayAheadTLForecastByDayValue;
    }
}
