package in.nmaloth.entity.account;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheAmount implements DataSerializable {

    private String transactionId;
    private LocalDateTime localDateTime;
    private Long amount;
    private Boolean debit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheAmount)) return false;
        CacheAmount that = (CacheAmount) o;
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
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        transactionId = dataInput.readUTF();
        localDateTime = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        amount = dataInput.readLong();
        debit = dataInput.readBoolean();
    }
}
