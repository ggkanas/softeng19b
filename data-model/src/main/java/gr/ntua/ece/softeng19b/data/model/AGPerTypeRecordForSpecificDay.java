package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class AGPerTypeRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private Timestamp dateTime; //*** NEW ***
    private double actualGenerationOutputValue;
    private Timestamp updateTime;
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

    public double getActualGenerationOutputValue() {
        return actualGenerationOutputValue;
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

    public void setActualGenerationOutputValue(double actualGenerationOutputValue) {
        this.actualGenerationOutputValue = actualGenerationOutputValue;
    }
}
