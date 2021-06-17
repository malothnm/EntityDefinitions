package in.nmaloth.entity.backups;


import in.nmaloth.entity.RegionNames;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.expiration.TimeToLiveExpiration;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ReplicateRegion(name = RegionNames.REPLAY_MESSAGES,ignoreIfExists = true)
@TimeToLiveExpiration(action = "DESTROY",timeout = "60")
public class ReplayRequest  implements DataSerializable {

    @Id
    private String messageId;
    private String instanceId;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(instanceId);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        messageId = dataInput.readUTF();
        instanceId = dataInput.readUTF();

    }
}
