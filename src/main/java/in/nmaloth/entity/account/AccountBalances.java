package in.nmaloth.entity.account;


import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountBalances  implements DataSerializable {

    private long postedBalance;
    private long memoDb;
    private long memoCr;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(postedBalance);
        dataOutput.writeLong(memoDb);
        dataOutput.writeLong(memoCr);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        this.postedBalance = dataInput.readLong();
        this.memoDb = dataInput.readLong();
        this.memoCr = dataInput.readLong();

    }
}
