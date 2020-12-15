package in.nmaloth.entity.account;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountBalances {

    private long postedBalance;
    private long memoDb;
    private long memoCr;
}
