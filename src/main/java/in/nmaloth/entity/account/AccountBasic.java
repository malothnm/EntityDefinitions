package in.nmaloth.entity.account;

import in.nmaloth.entity.BlockType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Region("accountBasic")
public class AccountBasic {


    @Id
    private String accountNumber;

    private AccountType accountType;

    private  Integer org;
    private Integer product;
    private BlockType blockType;
    private LocalDateTime dateBlockApplied;
    private BlockType previousBlockType;
    private LocalDateTime datePreviousBLockType;

    private String billingCurrencyCode;

    private String previousAccountNumber;
    private LocalDateTime dateTransfer;

    private String customerNumber;

    private String corporateNumber;

    private Map<BalanceTypes,Long> limitsMap;




}
