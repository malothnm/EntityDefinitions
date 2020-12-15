package in.nmaloth.entity.card;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.account.AccountDef;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
    private CardStatus cardStatus;
    private CardHolderType cardholderType;
    private BlockType blockType;
    private BlockType prevBlockType;
    private LocalDateTime dateBlockCode;
    private LocalDateTime datePrevBlockCode;
    private Integer waiverDaysActivation;
    private Integer cardReturnNumber;

    private String prevCardNumber;
    private LocalDateTime dateTransfer;

    private Set<AccountDef> accountDefSet;

    private String customerNumber;
    private String corporateNumber;


    private Map<PeriodicType, Map<LimitType,PeriodicCardAmount>>  periodicTypePeriodicCardLimitMap;


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
