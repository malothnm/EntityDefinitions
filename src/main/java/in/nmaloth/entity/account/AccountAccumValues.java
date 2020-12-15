package in.nmaloth.entity.account;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Region("accountAccum")
public class AccountAccumValues {

    @Id
    private String  accountId;

    @Indexed
    private String accountNumber;

    private Map<BalanceTypes,AccountBalances> balancesMap = new HashMap<>();


}
