package in.nmaloth.entity.account;

import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.gemfire.mapping.annotation.Indexed;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountKey implements DataSerializable {

    private String id;
    private String accountNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountKey)) return false;
        AccountKey that = (AccountKey) o;
        return getId().equals(that.getId()) &&
                getAccountNumber().equals(that.getAccountNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber());
    }

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(accountNumber);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        id = dataInput.readUTF();
        accountNumber = dataInput.readUTF();
    }
}
