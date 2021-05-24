package in.nmaloth.function.model.input;

import in.nmaloth.entity.account.BalanceTypes;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountInput implements DataSerializable {

    private String messageId;
    private String messageTypeId;
    private long transactionAmount;
    private boolean debit;
    private List<BalanceTypes> balanceTypesList;



    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(messageTypeId);
        dataOutput.writeLong(transactionAmount);
        dataOutput.writeBoolean(debit);
        dataOutput.writeInt(balanceTypesList.size());
        balanceTypesList.forEach(balanceTypes -> {
            try {
                balanceTypes.toData(dataOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        messageId = dataInput.readUTF();
        messageTypeId = dataInput.readUTF();
        transactionAmount = dataInput.readLong();
        debit = dataInput.readBoolean();
        balanceTypesList = new ArrayList<>();
        int balanceTypeListArraySize = dataInput.readInt();
        for(int i = 0; i < balanceTypeListArraySize; i ++ ){
            balanceTypesList.add(BalanceTypes.fromData(dataInput));
        }

    }
}
