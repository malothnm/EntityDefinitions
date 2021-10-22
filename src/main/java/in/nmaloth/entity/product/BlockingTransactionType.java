package in.nmaloth.entity.product;

import in.nmaloth.payments.constants.TransactionType;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockingTransactionType implements Comparable<BlockingTransactionType>, DataSerializable {

    private InternationalApplied internationalApplied;
    private TransactionType transactionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockingTransactionType)) return false;
        BlockingTransactionType that = (BlockingTransactionType) o;
        return getTransactionType() == that.getTransactionType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionType());
    }

    @Override
    public int compareTo(BlockingTransactionType o) {

        return this.transactionType.compareTo(o.getTransactionType());

    }

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(internationalApplied.getBlockInternational());
        dataOutput.writeUTF(transactionType.getTransactionType());
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        internationalApplied = InternationalApplied.identify(dataInput.readUTF());
        transactionType = TransactionType.identify(dataInput.readUTF());
    }
}
