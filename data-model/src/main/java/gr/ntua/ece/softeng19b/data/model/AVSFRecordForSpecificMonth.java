package gr.ntua.ece.softeng19b.data.model;

public class AVSFRecordForSpecificMonth extends AbstractEntsoeRecord {

  private int day;
  private double dayAheadTotalLoadForecastByDayValue;
  private double actualTotalLoadByDayValue;

  public void setDay(int day) {
    this.day = day;
  }

  public int getDay() {
    return day;
  }

  public void setActualTotalLoadByDayValue(double actualTotalLoadByDayValue) {
    this.actualTotalLoadByDayValue = actualTotalLoadByDayValue;
  }

  public double getActualTotalLoadByDayValue() {
    return actualTotalLoadByDayValue;
  }

  public void setDayAheadTotalLoadForecastByDayValue(double dayAheadTotalLoadForecastByDayValue) {
    this.dayAheadTotalLoadForecastByDayValue = dayAheadTotalLoadForecastByDayValue;
  }

  public double getActualTotalLoadByDayValue() {
    return actualTotalLoadByDayValue;
  }

}
