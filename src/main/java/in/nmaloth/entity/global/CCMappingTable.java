package in.nmaloth.entity.global;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReplicateRegion(name = RegionNames.CURRENCY_CODE_MAPPING,scope = ScopeType.DISTRIBUTED_ACK)
public class CCMappingTable implements DataSerializable {

    @Id
    private String currencyCode;

    private String alternateCode;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(currencyCode);
        dataOutput.writeUTF(alternateCode);

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        currencyCode = dataInput.readUTF();
        alternateCode = dataInput.readUTF();
    }
}
