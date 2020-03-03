package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class ATLRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private Timestamp dateTime; //*** NEW ***
    private double actualTotalLoadValue;
    private Timestamp updateTime;

    public ATLRecordForSpecificDay() {
        super(DataSet.ActualTotalLoad);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getActualTotalLoadValue() {
        return actualTotalLoadValue;
    }
    //*** NEW ***
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp UpdateTime) {
        this.updateTime = updateTime;
    }

    public void setActualTotalLoadValue(double actualTotalLoadValue) {
        this.actualTotalLoadValue = actualTotalLoadValue;
    }
}
