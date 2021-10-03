package in.nmaloth.entity.product;

public enum Strategy {

    CHALLENGER("CHALLENGER"),
    CHAMPION("CHAMPION")
    ;

    private String strategy;

    Strategy(String strategy){
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }
}
