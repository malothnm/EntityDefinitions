package in.nmaloth.function.model.temp;

import in.nmaloth.entity.card.LimitType;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheCardTempAccum {


    private LimitType limitType;
    private long accumAmount;
    private int accumCount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheCardTempAccum)) return false;
        CacheCardTempAccum that = (CacheCardTempAccum) o;
        return getLimitType() == that.getLimitType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLimitType());
    }
}
