package in.nmaloth.entity.card;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class PeriodicCardAmount  implements DataSerializable {

    private LimitType limitType;
    private Long transactionAmount;
    private Integer transactionNumber;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(limitType.getLimitType());
        dataOutput.writeLong(transactionAmount);
        dataOutput.writeInt(transactionNumber);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        limitType = LimitType.identify(dataInput.readUTF());
        transactionAmount = dataInput.readLong();
        transactionNumber = dataInput.readInt();
    }
}
