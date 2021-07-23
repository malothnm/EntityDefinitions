package in.nmaloth.entity.product;

import in.nmaloth.entity.RegionNames;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ReplicateRegion(name = RegionNames.CHIP_PARAM,scope = ScopeType.DISTRIBUTED_ACK)
public class ChipParameters /* implements DataSerializable */  {

    @Id
    private ChipKey chipKey;

    private boolean[] tvrApproveDecline;
    private boolean[] cvrApproveDecline;


//    @Override
//    public void toData(DataOutput dataOutput) throws IOException {
//
//        chipKey.toData(dataOutput);
//        dataOutput.writeInt(tvrApproveDecline.length);
//        for (boolean bool : tvrApproveDecline) {
//            dataOutput.writeBoolean(bool);
//        }
//        dataOutput.writeInt(cvrApproveDecline.length);
//        for (boolean bool : cvrApproveDecline) {
//            dataOutput.writeBoolean(bool);
//        }
//
//    }
//
//    @Override
//    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
//
//        chipKey.fromData(dataInput);
//        int length = dataInput.readInt();
//        tvrApproveDecline = new boolean[length];
//        for (int i = 0; i < length; i ++){
//            tvrApproveDecline[i] = dataInput.readBoolean();
//        }
//        length = dataInput.readInt();
//        cvrApproveDecline = new boolean[length];
//        for (int i = 0; i < length; i ++){
//            cvrApproveDecline[i] = dataInput.readBoolean();
//        }
//    }
}
