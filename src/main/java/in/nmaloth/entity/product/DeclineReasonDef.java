package in.nmaloth.entity.product;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("declineReason")
public class DeclineReasonDef {


    @Id
    private String action;

    private Boolean approveDecline;
    private Integer declineReason;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeclineReasonDef)) return false;
        DeclineReasonDef that = (DeclineReasonDef) o;
        return getAction().equals(that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAction());
    }
}
