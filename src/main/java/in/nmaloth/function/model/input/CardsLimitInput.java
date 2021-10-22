package in.nmaloth.function.model.input;

import in.nmaloth.entity.card.LimitType;
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

public class CardsLimitInput implements DataSerializable {

    private String messageId;
    private String cardId;
    private String messageTypeId;
    private long transactionAmount;
    private boolean debit;
    private List<LimitType> limitTypes;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(cardId);
        dataOutput.writeUTF(messageTypeId);
        dataOutput.writeLong(transactionAmount);
        dataOutput.writeBoolean(debit);
        dataOutput.writeInt(limitTypes.size());
        limitTypes.forEach(limitType -> {
            try {
                limitType.toData(dataOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        messageId = dataInput.readUTF();
        cardId = dataInput.readUTF();
        messageTypeId = dataInput.readUTF();
        transactionAmount = dataInput.readLong();
        debit = dataInput.readBoolean();
        limitTypes = new ArrayList<>();
        int balanceTypeListArraySize = dataInput.readInt();
        for(int i = 0; i < balanceTypeListArraySize; i ++ ){
            limitTypes.add(LimitType.fromData(dataInput));
        }
    }
}
