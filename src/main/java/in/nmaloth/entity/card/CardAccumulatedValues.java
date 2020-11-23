package in.nmaloth.entity.card;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CardAccumulatedValues {

    @Id
    private String id;

    @Indexed
    private String cardNumber;

    private Integer org;
    private Integer product;

    private Map<LimitType,Long> amountMap;
    private Map<LimitType,Integer> txnNumberMap;

    private Map<PeriodicType,PeriodicCardAccumulatedValue> periodicCardAccumulatedValueMap;



}
