package in.nmaloth.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Region("accountAccum")
public class AccountAccumValues {

    @Id
    private AccountKey accountKey;

    @Indexed
    private String accountNumber;

    private Map<BalanceTypes,AccountBalances> balancesMap = new HashMap<>();


}
