package in.nmaloth.entity.logs;

public enum ReversalStatus {

    REVERSED("R"),
    NOT_REVERSED("N")
    ;

    private String reversalStatus;

    ReversalStatus(String reversalStatus){
        this.reversalStatus = reversalStatus;
    }

    public String getReversalStatus() {
        return reversalStatus;
    }
    public static ReversalStatus identify(String reversalStatus){
        if(reversalStatus.equalsIgnoreCase("R")){
            return ReversalStatus.REVERSED;
        }
        return ReversalStatus.NOT_REVERSED;
    }
}
