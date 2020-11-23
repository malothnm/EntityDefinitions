package in.nmaloth.entity.card;

import in.nmaloth.entity.BlockType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("cardBasic")

public class CardsBasic {

    @Id
    private String cardNumber;

    private Integer product;
    private Integer org;
    private String cardStatus;
    private String cardholderType;
    private BlockType blockType;
    private BlockType prevBlockType;
    private LocalDateTime dateBlockCode;
    private LocalDateTime datePrevBlockCode;
    private Integer waiverDaysActivation;
    private Integer cardReturnNumber;
    private Map<LimitType,Long> cardLimits;
    private Map<PeriodicType,PeriodicCardLimit>  periodicTypePeriodicCardLimitMap;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardsBasic)) return false;
        CardsBasic that = (CardsBasic) o;
        return getCardNumber().equals(that.getCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber());
    }
}
