package in.nmaloth.entity.card;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum CardAction {

        NO_ACTION("0"),
        NEW_CARD("1"),
        ADDITIONAL_CARD("2"),
        REPLACEMENT_CARD("3"),
        EMERGENCY_REPLACEMENT_CARD("4"),
        REISSUE_CARD("5");

        private String cardAction;

        CardAction(String cardAction){
                this.cardAction = cardAction;
        }

        public String getCardAction() {
                return cardAction;
        }

        public static CardAction identify(String cardAction){
                switch (cardAction){

                        case "0": return CardAction.NO_ACTION;
                        case "1": return CardAction.NEW_CARD;
                        case "2": return CardAction.ADDITIONAL_CARD;
                        case "3": return CardAction.REPLACEMENT_CARD;
                        case "4": return CardAction.EMERGENCY_REPLACEMENT_CARD;
                        case "5": return CardAction.REISSUE_CARD;

                }

                log.error(" Invalid Card Action .. {}",cardAction);
                throw new InvalidEnumConversion("Invalid Card Action");
        }

        public void toData(DataOutput dataOutput) throws IOException {
                dataOutput.writeUTF(cardAction);
        }

        public static CardAction fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
                return identify(dataInput.readUTF());
        }
}
