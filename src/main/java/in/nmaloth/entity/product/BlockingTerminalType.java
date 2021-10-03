package in.nmaloth.entity.product;

import in.nmaloth.payments.constants.TerminalType;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BlockingTerminalType implements Comparable<BlockingTerminalType>{

    private InternationalApplied internationalApplied;
    private TerminalType terminalType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockingTerminalType)) return false;
        BlockingTerminalType that = (BlockingTerminalType) o;
        return getTerminalType() == that.getTerminalType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTerminalType());
    }

    @Override
    public int compareTo(BlockingTerminalType o) {
        return this.terminalType.compareTo(o.getTerminalType());

    }
}

