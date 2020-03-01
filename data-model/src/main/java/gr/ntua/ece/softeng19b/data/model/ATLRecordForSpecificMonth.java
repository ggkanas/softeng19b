package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month; //*** NEW ***
    private double actualTotalLoadByDayValue;

    public ATLRecordForSpecificMonth() {
        super(DataSet.ActualTotalLoad);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


    public ATLRecordForSpecificMonth() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualTotalLoadByDayValue() {
        return actualTotalLoadByDayValue;
    }

    public void setActualTotalLoadByDayValue(double actualTotalLoadByDayValue) {
        this.actualTotalLoadByDayValue = actualTotalLoadByDayValue;
    }
}
