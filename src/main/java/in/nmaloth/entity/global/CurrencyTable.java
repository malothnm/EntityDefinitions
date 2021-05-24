package in.nmaloth.entity.global;


import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReplicateRegion(name = RegionNames.CURRENCY_CONVERSION_TABLE,scope = ScopeType.DISTRIBUTED_ACK)
public class CurrencyTable implements DataSerializable {

    @Id
    private CurrencyKey currencyKey;
    private long buyRate;
    private long sellRate;
    private long midRate;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        currencyKey.toData(dataOutput);
        dataOutput.writeLong(buyRate);
        dataOutput.writeLong(sellRate);
        dataOutput.writeLong(midRate);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        currencyKey = new CurrencyKey();
        currencyKey.fromData(dataInput);
        buyRate = dataInput.readLong();
        sellRate = dataInput.readLong();
        midRate = dataInput.readLong();

    }

    @Override
    public String toString() {
        return "CurrencyTable{" +
                "currencyKey=" + currencyKey +
                ", buyRate=" + buyRate +
                ", sellRate=" + sellRate +
                ", midRate=" + midRate +
                '}';
    }
}
