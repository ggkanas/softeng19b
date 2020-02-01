package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private DateTime dateTime; //*** NEW ***
    private double actualTotalLoadValue;
    private DateTime UpdateTime;

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
    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(DateTime UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public void setActualTotalLoadValue(double actualTotalLoadValue) {
        this.actualTotalLoadValue = actualTotalLoadValue;
    }
}
