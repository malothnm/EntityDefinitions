package in.nmaloth.entity.card;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
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

    public static CardStatus identify(String cardStatus){
        switch (cardStatus){
            case "A": return CardStatus.ACTIVE;
            case "F": return CardStatus.FRAUD;
            case "T": return CardStatus.TRANSFER;
            case "P": return CardStatus.PURGED;
            case "I": return CardStatus.INACTIVE;
        }
        log.error(" Invalid Card Status ...{}", cardStatus);
        throw new InvalidEnumConversion("Invalid Card Status");
    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(cardStatus);
    }

    public static CardStatus fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }

}
