package in.nmaloth.entity.card;

public enum CardStatus {

    ACTIVE("A"),
    FRAUD("F"),
    PURGED("P"),
    TRANSFER("T"),
    INACTIVE("I");




    private String cardStatus;

    CardStatus(String cardStatus){
        this.cardStatus = cardStatus;
    }

    public String getCardStatus(){
        return cardStatus;
    }
}
