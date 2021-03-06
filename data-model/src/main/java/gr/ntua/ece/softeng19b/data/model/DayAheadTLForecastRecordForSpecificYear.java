package gr.ntua.ece.softeng19b.data.model;

public class DayAheadTLForecastRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year;
    private double dayAheadTotalLoadForecastByMonthValue;

    public DayAheadTLForecastRecordForSpecificYear() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getDayAheadTotalLoadForecastByMonthValue() {
        return dayAheadTotalLoadForecastByMonthValue;
    }


    public void setDayAheadTotalLoadForecastByMonthValue(double dayAheadTotalLoadForecastByMonthValueValue) {
        this.dayAheadTotalLoadForecastByMonthValue = dayAheadTotalLoadForecastByMonthValue;
    }
}
