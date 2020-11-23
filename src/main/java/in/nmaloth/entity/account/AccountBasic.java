package in.nmaloth.entity.account;

import in.nmaloth.entity.BlockType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Region("accountBasic")
public class AccountBasic {


    @Id
    private String accountNumber;

    private BlockType blockType;
    private String billingCurrencyCode;

    private Map<BalanceTypes,Long> limitsMap;




}
