package in.nmaloth.entity.card;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PeriodicCardAccumulatedValue {

    private LimitType limitType;
    private Map<LimitType,Long> limitAmountMap ;
    private Map<LimitType,Integer> limitNumberMap;

}
