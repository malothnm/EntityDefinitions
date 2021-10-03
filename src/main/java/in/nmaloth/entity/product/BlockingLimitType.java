package in.nmaloth.entity.product;

import in.nmaloth.entity.card.LimitType;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BlockingLimitType implements Comparable<BlockingLimitType> {

    private InternationalApplied internationalApplied;
    private LimitType limitType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockingLimitType)) return false;
        BlockingLimitType that = (BlockingLimitType) o;
        return getLimitType().equals(that.getLimitType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLimitType());
    }

    @Override
    public int compareTo(BlockingLimitType o) {
        return this.limitType.compareTo(o.getLimitType());
    }
}
