package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year; // *** NEW ***
    private double actualDataLoadByMonthValue;

    public ATLRecordForSpecificYear() {
        super(DataSet.ActualTotalLoad);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public ATLRecordForSpecificYear() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualDataLoadByMonthValue() {
        return actualDataLoadByMonthValue;
    }

    public void setActualDataLoadByMonthValue(double actualDataLoadByMonthValue) {
        this.actualDataLoadByMonthValue = actualDataLoadByMonthValue;
    }
}
