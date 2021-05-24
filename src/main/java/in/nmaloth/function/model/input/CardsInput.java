package in.nmaloth.function.model.input;

import in.nmaloth.payments.constants.*;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CardsInput implements DataSerializable {

    private String messageId;
    private String messageTypeId;
    private LocalDate expiryDate;
    private PurchaseTypes purchaseTypes;
    private TransactionType transactionType;
    private TerminalType terminalType;
    private InstallmentType installmentType;
    private CashBack cashBack;
    private EntryMode entryMode;
    private RecurringTrans recurringTrans;




    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(messageTypeId);
        dataOutput.writeUTF(expiryDate.format(DateTimeFormatter.ISO_DATE.ISO_DATE));
        purchaseTypes.toData(dataOutput);
        transactionType.toData(dataOutput);
        terminalType.toData(dataOutput);
        installmentType.toData(dataOutput);
        cashBack.toData(dataOutput);
        entryMode.toData(dataOutput);
        recurringTrans.toData(dataOutput);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        messageId = dataInput.readUTF();
        messageTypeId = dataInput.readUTF();
        expiryDate = LocalDate.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE);
        purchaseTypes = PurchaseTypes.fromData(dataInput);
        transactionType = TransactionType.fromData(dataInput);
        terminalType = TerminalType.fromData(dataInput);
        installmentType = InstallmentType.fromData(dataInput);
        cashBack = CashBack.fromData(dataInput);
        entryMode = EntryMode.fromData(dataInput);
        recurringTrans = RecurringTrans.fromData(dataInput);
    }
}
