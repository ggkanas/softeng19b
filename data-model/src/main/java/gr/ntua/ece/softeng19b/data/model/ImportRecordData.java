package gr.ntua.ece.softeng19b.data.model;

public class ImportRecordData {

    private long totalRecordsInFile;
    private long totalRecordsImported;
    private long totalRecordsInDatabase;

    public ImportRecordData(long inFile, long imported, long inDatabase) {
        long totalRecordsInFile = inFile;
        long totalRecordsImported = imported;
        long totalRecordsInDatabase = inDatabase;
    }

    public long getTotalRecordsInDatabase() {
        return totalRecordsInDatabase;
    }
    public long getTotalRecordsInFile() {
        return totalRecordsInFile;
    }
    public long getTotalRecordsImported() {
        return totalRecordsImported;
    }

}
