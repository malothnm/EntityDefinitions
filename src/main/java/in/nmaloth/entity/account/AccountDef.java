package in.nmaloth.entity.account;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDef implements DataSerializable {

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

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(accountNumber);
        accountType.toData(dataOutput);
        dataOutput.writeUTF(billingCurrencyCode);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        accountNumber = dataInput.readUTF();
        accountType = AccountType.fromData(dataInput);
        billingCurrencyCode = dataInput.readUTF();
    }
}
