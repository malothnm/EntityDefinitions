package in.nmaloth.entity.card;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Region("cardAccum")
public class CardAccumulatedValues {

    @Id
    private String cardNumber;

    private Integer org;
    private Integer product;

    private Map<PeriodicType, Map<LimitType,PeriodicCardAmount>> periodicCardAccumulatedValueMap;



}
