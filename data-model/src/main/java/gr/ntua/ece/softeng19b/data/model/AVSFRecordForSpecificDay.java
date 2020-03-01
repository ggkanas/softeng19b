package gr.ntua.ece.softeng19b.data.model;

public class AVSFRecordForSpecificDay extends AbstractEntsoeRecord {

  private int day;
  private Timestamp dateTime;
  private double dayAheadTotalLoadForecastValue;
  private double actualTotalLoadValue;

  public void setDay(int day) {
    this.day = day;
  }

  public int getDay() {
    return day;
  }

  public void setDateTime(Timestamp dateTime) {
    this.dateTime = dateTime;
  }

  public Timestamp getDayTime() {
    return dayTime;
  }

  public void setActualTotalLoadValue(double actualTotalLoadValue) {
    this.actualTotalLoadValue = actualTotalLoadValue;
  }

  public double getActualTotalLoadValue() {
    return actualTotalLoadValue;
  }

}
