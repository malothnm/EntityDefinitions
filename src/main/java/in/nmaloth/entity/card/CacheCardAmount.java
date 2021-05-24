package in.nmaloth.entity.card;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheCardAmount implements DataSerializable {

    private String transactionId;
    private LocalDateTime localDateTime;
    private Long amount;
    private Boolean debit;
    private List<LimitType> limitTypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheCardAmount)) return false;
        CacheCardAmount that = (CacheCardAmount) o;
        return getTransactionId().equals(that.getTransactionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionId());
    }

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(transactionId);
        dataOutput.writeUTF(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        dataOutput.writeLong(amount);
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

        transactionId = dataInput.readUTF();
        localDateTime = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        amount = dataInput.readLong();
        debit = dataInput.readBoolean();
        limitTypes = new ArrayList<>();
        int limitTypesSize = dataInput.readInt();
        for (int i = 0; i < limitTypesSize; i++){
            limitTypes.add(LimitType.fromData(dataInput));
        }
    }
}
