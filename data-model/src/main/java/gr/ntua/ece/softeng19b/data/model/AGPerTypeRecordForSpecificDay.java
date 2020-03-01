package gr.ntua.ece.softeng19b.data.model;

public class AGPerTypeRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private DateTime dateTime; //*** NEW ***
    private double actualGenerationOutputValue;
    private DateTime UpdateTime;
    private String productionType;

    public AGPerTypeRecordForSpecificDay() {
        super(DataSet.ActualGenerationOutput);
    }

    public String getProductionType() {
        return productionType;
    }
    
    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }
    
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getactualGenerationOutputValue() {
        return actualGenerationOutputValue;
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

    public void setactualGenerationOutputValue(double actualGenerationOutputValue) {
        this.actualGenerationOutputValue = actualGenerationOutputValue;
    }
}
