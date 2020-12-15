package in.nmaloth.entity.product;

import in.nmaloth.entity.account.AccountType;
import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.card.LimitType;
import in.nmaloth.entity.card.PeriodicType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("productDef")

public class ProductDef {

   @Id
    private ProductId productId;

    private Integer cardsValidityMonthNew;
    private Integer cardsValidityMonthReplace;
    private Integer cardsValidityMonthReIssue;
    private Integer dateRangeNewExpDate;
    private Integer cardsWaiverActivationDays;
    private Integer daysToCardsValid;
    private Integer cardsReturn;
    private Boolean cardsActivationRequired;
    private Integer serviceCode;
    private Map<BalanceTypes,Long> limitPercents;
    private AccountType primaryAccountType;
    private String billingCurrencyCode;


}
