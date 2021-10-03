package in.nmaloth.entity.product;

import in.nmaloth.payments.constants.TransactionType;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockingTransactionType implements Comparable<BlockingTransactionType> {

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
}
