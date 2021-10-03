package in.nmaloth.entity.product;

import in.nmaloth.entity.account.BalanceTypes;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockingBalanceType implements Comparable<BlockingBalanceType> {

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
}
