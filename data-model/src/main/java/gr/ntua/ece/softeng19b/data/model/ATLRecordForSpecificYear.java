package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year; // *** NEW ***
    private double actualTotalLoadByMonthValue;
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public ATLRecordForSpecificYear() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualTotalLoadByMonthValue() {
        return actualTotalLoadByMonthValue;
    }

    public void setActualTotalLoadByMonthValue(double actualTotalLoadByMonthValue) {
        this.actualTotalLoadByMonthValue = actualTotalLoadByMonthValue;
    }
}
