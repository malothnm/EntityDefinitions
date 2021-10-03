package in.nmaloth.entity.product;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.payments.constants.PurchaseTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReplicateRegion(name = RegionNames.AUTH_CRITERIA,scope = ScopeType.DISTRIBUTED_ACK)
public class ProductAuthCriteria {

    @Id
    private ProductAuthCriteriaKey productAuthCriteriaKey;

    // Crypto Patameters
    private boolean supportFallbackToMagStripe;
    private boolean validateContactCVMList;
    private List<CVM> supportedContactCVM;
    private Strategy strategy;

    // Blocking Criteria
    private IncludeExclude blockingCountries;
    private List<String> countryCodesBlocked;
    private IncludeExclude blockingCurrency;
    private List<String> currencyCodesBlocked;
    private IncludeExclude blockingStates;
    private List<String> stateCodesBlocked;
    private IncludeExclude blockingMCC;
    private List<MCCRange> mccBlocked;
    private IncludeExclude blockingPurchaseTypes;
    private List<PurchaseTypes> purchaseTypesBlocked;
    private IncludeExclude blockingLimitTypes;
    private List<BlockingLimitType> limitTypesBlocked;
    private IncludeExclude blockingBalanceTypes;
    private List<BlockingBalanceType> balanceTypesBlocked;
    private IncludeExclude blockingTransactionTypes;
    private List<BlockingTransactionType> transactionTypesBlocked;
    private IncludeExclude blockTerminalTypes;
    private List<BlockingTerminalType> terminalTypesBlocked;


}
