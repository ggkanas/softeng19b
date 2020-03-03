package gr.ntua.ece.softeng19b.data.model;

public class AGPerTypeRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year;
    private double actualGenerationOutputByMonthValue;
    private String productionType;
    
    public AGPerTypeRecordForSpecificYear () {
        super(DataSet.AggregatedGenerationPerType);
    }
   
    public String getProductionType() {
        return productionType;
    }
    
    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }    
    
   public double getActualGenerationOutputByMonthValue() {
        return actualGenerationOutputByMonthValue;
    }
  
    public void setActualGenerationOutputByMonthValue (double actualGenerationOutputByMonthValue) {
        this.actualGenerationOutputByMonthValue = actualGenerationOutputByMonthValue;
    }
}
