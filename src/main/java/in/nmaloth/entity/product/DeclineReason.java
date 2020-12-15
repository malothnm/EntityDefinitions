package in.nmaloth.entity.product;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DeclineReason {

    private String action;
    private Boolean approveDecline;
    private String declineReason;
    private int priority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeclineReason)) return false;
        DeclineReason that = (DeclineReason) o;
        return getAction().equals(that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAction());
    }
}
