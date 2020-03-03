package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class AVSFRecordForSpecificDay extends AbstractEntsoeRecord {

  private int day;
  private Timestamp dateTime;
  private double dayAheadTotalLoadForecastValue;
  private double actualTotalLoadValue;

  public AVSFRecordForSpecificDay() {
    super(DataSet.ActualVSForecastedTotalLoad);
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getDay() {
    return day;
  }

  public void setDateTime(Timestamp dateTime) {
    this.dateTime = dateTime;
  }

  public Timestamp getDateTime() {
    return dateTime;
  }

  public void setActualTotalLoadValue(double actualTotalLoadValue) {
    this.actualTotalLoadValue = actualTotalLoadValue;
  }

  public double getActualTotalLoadValue() {
    return actualTotalLoadValue;
  }
  
  public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
    this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
  }
  
  public double getDayAheadTotalLoadForecastValue() {
    return dayAheadTotalLoadForecastValue;
  }

}
