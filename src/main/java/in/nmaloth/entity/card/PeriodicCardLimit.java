package in.nmaloth.entity.card;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class PeriodicCardLimit {

    private Map<LimitType,Long> transactionAmountLimit;
    private Map<LimitType,Integer> transactionNumberLimit;

}
