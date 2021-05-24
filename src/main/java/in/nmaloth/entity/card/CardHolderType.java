package in.nmaloth.entity.card;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
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

    public static CardHolderType identify(String cardHolderType){
        switch (cardHolderType){
            case "P": return CardHolderType.PRIMARY;
            case "S": return CardHolderType.SECONDARY;
            case "A": return CardHolderType.ADDITIONAL;
        }
        log.error("Invalid CardHolder Type ...{}",cardHolderType);
        throw new InvalidEnumConversion("Invalid Card Holder Type");
    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(cardHolderType);
    }

    public static CardHolderType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }

}
