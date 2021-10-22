package in.nmaloth.function.model.input;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.card.LimitType;
import in.nmaloth.payments.constants.*;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CardsInput {

    private String messageId;
    private String cardId;
    private String messageTypeId;
    private LocalDate expiryDate;
    private PurchaseTypes purchaseTypes;
    private TransactionType transactionType;
    private TerminalType terminalType;
    private InstallmentType installmentType;
    private CashBack cashBack;
    private EntryMode entryMode;
    private RecurringTrans recurringTrans;
    private International international;
    private List<LimitType> limitTypeList;
    private List<String> plasticIdList;
    private Long amount;
    private boolean debit;






    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(cardId);
        dataOutput.writeUTF(messageTypeId);
        dataOutput.writeUTF(expiryDate.format(DateTimeFormatter.ISO_DATE.ISO_DATE));
        purchaseTypes.toData(dataOutput);
        transactionType.toData(dataOutput);
        terminalType.toData(dataOutput);
        installmentType.toData(dataOutput);
        cashBack.toData(dataOutput);
        entryMode.toData(dataOutput);
        recurringTrans.toData(dataOutput);
        dataOutput.writeUTF(international.getInternational());
        dataOutput.writeInt(limitTypeList.size());

        if(limitTypeList.size() > 0){
            limitTypeList.forEach(limitType -> {
                try {
                    dataOutput.writeUTF(limitType.getLimitType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        dataOutput.writeInt(plasticIdList.size());
        for (String plasticId:plasticIdList) {
            dataOutput.writeUTF(plasticId);
        }

        dataOutput.writeLong(amount);
        dataOutput.writeBoolean(debit);
    }

    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        messageId = dataInput.readUTF();
        cardId = dataInput.readUTF();
        messageTypeId = dataInput.readUTF();
        expiryDate = LocalDate.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE);
        purchaseTypes = PurchaseTypes.fromData(dataInput);
        transactionType = TransactionType.fromData(dataInput);
        terminalType = TerminalType.fromData(dataInput);
        installmentType = InstallmentType.fromData(dataInput);
        cashBack = CashBack.fromData(dataInput);
        entryMode = EntryMode.fromData(dataInput);
        recurringTrans = RecurringTrans.fromData(dataInput);
        international = International.identify(dataInput.readUTF());
        limitTypeList = new ArrayList<>();
        int size = dataInput.readInt();
        if(size > 0){
            for(int i = 0; i < size; i ++ ){
                limitTypeList.add(LimitType.identify(dataInput.readUTF()));
            }
        }
        plasticIdList = new ArrayList<>();
        size = dataInput.readInt();

        for(int i = 0; i < size; i ++){
            plasticIdList.add(dataInput.readUTF());
        }

        amount = dataInput.readLong();
        debit = dataInput.readBoolean();
    }
}
