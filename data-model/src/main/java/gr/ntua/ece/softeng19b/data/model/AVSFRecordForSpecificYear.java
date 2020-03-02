package gr.ntua.ece.softeng19b.data.model;

public class AVSFRecordForSpecificYear extends AbstractEntsoeRecord {

  private int day;
  private double dayAheadTotalLoadForecastByMonthValue;
  private double actualTotalLoadByMonthValue;

  public AVSFRecordForSpecificYear() {
    super(DataSet.ActualVSForecastedTotalLoad);
  }  
  
  public void setDay(int day) {
    this.day = day;
  }

  public int getDay() {
    return day;
  }

  public void setActualTotalLoadByMonthValue(double actualTotalLoadByMonthValue) {
    this.actualTotalLoadByMonthValue = actualTotalLoadByMonthValue;
  }

  public double getActualTotalLoadByMonthValue() {
    return actualTotalLoadByMonthValue;
  }

  public void setDayAheadTotalLoadForecastByMonthValue(double dayAheadTotalLoadForecastByMonthValue) {
    this.dayAheadTotalLoadForecastByMonthValue = dayAheadTotalLoadForecastByMonthValue;
  }

  public double getActualTotalLoadByMonthValue() {
    return actualTotalLoadByMonthValue;
  }

}
