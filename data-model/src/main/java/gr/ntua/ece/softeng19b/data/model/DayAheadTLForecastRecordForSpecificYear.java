package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year;
    private double YearAheadTotalLoadForecastByMonthValue;

    public DayAheadTotalLoadForecastRecordForSpecificYear() {
        super(DataSet.DayAheadTotalLoad);
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double geDayAheadTLForecastByMonthValue() {
        return DayAheadTLForecastByMonthValue;
    }


    public void setDayAheadTLForecastByMonthValue(double DayAheadTLForecastByMonthValueValue) {
        this.DayAheadTLForecastByMonthValue = DayAheadTLForecastByMonthValue;
    }
}
