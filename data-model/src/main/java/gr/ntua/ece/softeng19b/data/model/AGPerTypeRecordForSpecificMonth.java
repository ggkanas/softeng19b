package gr.ntua.ece.softeng19b.data.model;

public class AGPerTypeRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double actualGenerationOutputByDayValue;
    private String productionType;


    public AGPerTypeRecordForSpecificMonth() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
