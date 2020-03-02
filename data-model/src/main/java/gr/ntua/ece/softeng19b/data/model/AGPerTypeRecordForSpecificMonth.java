package gr.ntua.ece.softeng19b.data.model;

public class AGPerTypeRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month;
    private double actualGenerationOutputByDayValue;
    private String productionType;
    
    public AGPerTypeRecordForSpecificMonth() {
        super(DataSet.AggregatedGenerationPerType);
    }
   
    public String getProductionType() {
        return productionType;
    }
    
    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }    
    
   public double getActualGenerationOutputByDayValue() {
        return actualGenerationOutputByDayValue;
    }
  
    public void setActualGenerationOutputByDayValue (double actualGenerationOutputByDayValue) {
        this.actualGenerationOutputByDayValue = actualGenerationOutputByDayValue;
    }
}
