package in.nmaloth.entity.card;

public enum  CardHolderType {

    PRIMARY("P"),
    SECONDARY("S"),
    ADDITIONAL("A");

    private String cardHolderType;

    CardHolderType(String cardHolderType){
        this.cardHolderType = cardHolderType;
    }

    public String getCardHolderType(){
        return cardHolderType;
    }
}
