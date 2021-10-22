package in.nmaloth.entity.product;

import in.nmaloth.entity.account.BalanceTypes;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockingBalanceType implements Comparable<BlockingBalanceType>, DataSerializable {

    private InternationalApplied internationalApplied;
    private BalanceTypes balanceTypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockingBalanceType)) return false;
        BlockingBalanceType that = (BlockingBalanceType) o;
        return getBalanceTypes() == that.getBalanceTypes();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalanceTypes());
    }

    @Override
    public int compareTo(BlockingBalanceType o) {

        return this.balanceTypes.compareTo(o.getBalanceTypes());
    }

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(internationalApplied.getBlockInternational());
        dataOutput.writeUTF(balanceTypes.getBalanceTypes());
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        internationalApplied = InternationalApplied.identify(dataInput.readUTF());
        balanceTypes = BalanceTypes.identify(dataInput.readUTF());
    }
}
