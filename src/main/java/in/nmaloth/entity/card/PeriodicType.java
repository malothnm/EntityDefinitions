package in.nmaloth.entity.card;

public enum PeriodicType {

    DAILY("DLY"),
    MONTHLY("MTH"),
    CYCLE_TO_DATE("CTD"),
    YEARLY("YEAR");


    private String periodicType;

    PeriodicType(String periodicType) {
        this.periodicType = periodicType;
    }

    public String getPeriodicType() {
        return periodicType;
    }
}
