package gr.ntua.ece.softeng19b.data.model;

public class AggregatedGenerationPerTypeRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year;
    private double actualGenerationOutputByMonthValue;
    private String productionType;
    
    public AggregatedGenerationPerTypeRecordForSpecificYear () {
        super(DataSet.actualGenerationOutputByMonth);
    }
   
    public String getProductionType() {
        return productionType;
    }
    
    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }    
    
   public double actualGenerationOutputByMonthValue() {
        return actualGenerationOutputByMonthValue;
    }
  
    public void setAggregatedGenerationPerTypeRecordForSpecificYear (double actualGenerationOutputByMonthValue) {
        this.actualGenerationOutputByMonthValue = actualGenerationOutputByMonthValue;
    }
}
