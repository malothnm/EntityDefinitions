package in.nmaloth.entity.account;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDef {

    private String accountNumber;
    private AccountType accountType;
    private String billingCurrencyCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDef)) return false;
        AccountDef that = (AccountDef) o;
        return Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                getAccountType() == that.getAccountType() &&
                Objects.equals(getBillingCurrencyCode(), that.getBillingCurrencyCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getAccountType(), getBillingCurrencyCode());
    }
}
