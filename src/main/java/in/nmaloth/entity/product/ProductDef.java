package in.nmaloth.entity.product;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.entity.account.AccountType;
import in.nmaloth.entity.account.BalanceTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ReplicateRegion(name = RegionNames.PRODUCT_DEF, scope = ScopeType.DISTRIBUTED_ACK)

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
    private Map<BalanceTypes, Long> limitPercents;
    private AccountType primaryAccountType;
    private String billingCurrencyCode;
    private boolean expiryDateMMYY;

    private boolean blockInstallments;
    private boolean blockCashBack;
    private boolean blockInternational;
    private boolean supportAVS;



}
