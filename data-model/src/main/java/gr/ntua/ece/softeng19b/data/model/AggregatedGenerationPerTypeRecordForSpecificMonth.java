package gr.ntua.ece.softeng19b.data.model;

public class AggregatedGenerationPerTypeRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int month;
    private double actualGenerationOutputByDayValue;

    public AggregatedGenerationPerTypeRecordForSpecificMonth() {
        super(DataSet.AggregatedGenerationPerType);
    }

   public double actualGenerationOutputByDayValue() {
        return actualGenerationOutputByDayValue;
    }

    public void setAggregatedGenerationPerTypeRecordForSpecificMonth (double actualGenerationOutputByDayValue) {
        this.actualGenerationOutputByDayValue = actualGenerationOutputByDayValue;
    }
}
