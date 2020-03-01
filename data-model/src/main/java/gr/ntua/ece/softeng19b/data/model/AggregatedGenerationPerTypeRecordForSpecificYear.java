package gr.ntua.ece.softeng19b.data.model;

public class AggregatedGenerationPerTypeRecordForSpecificYear extends AbstractEntsoeRecord {

    private int year;
    private double actualGenerationOutputByMonthValue;

    public AggregatedGenerationPerTypeRecordForSpecificYear () {
        super(DataSet.AggregatedGenerationPerType);
    }

   public double actualGenerationOutputByMonthValue() {
        return actualGenerationOutputByMonthValue;
    }

    public void setAggregatedGenerationPerTypeRecordForSpecificYear (double actualGenerationOutputByMonthValue) {
        this.actualGenerationOutputByMonthValue = actualGenerationOutputByMonthValue;
    }
}
