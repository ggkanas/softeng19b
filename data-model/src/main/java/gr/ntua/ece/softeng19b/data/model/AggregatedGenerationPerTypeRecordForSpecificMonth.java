package gr.ntua.ece.softeng19b.data.model;

public class AggregatedGenerationPerTypeRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month;
    private double actualGenerationOutputByDayValue;
    private String productionType;
    
    public AggregatedGenerationPerTypeRecordForSpecificMonth() {
        super(DataSet.actualGenerationOutputByDay);
    }
   
    public String getProductionType() {
        return productionType;
    }
    
    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }    
    
   public double actualGenerationOutputByDayValue() {
        return actualGenerationOutputByDayValue;
    }
  
    public void setAggregatedGenerationPerTypeRecordForSpecificMonth (double actualGenerationOutputByDayValue) {
        this.actualGenerationOutputByDayValue = actualGenerationOutputByDayValue;
    }
}
