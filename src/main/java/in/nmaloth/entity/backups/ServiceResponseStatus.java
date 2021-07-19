package in.nmaloth.entity.backups;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.expiration.TimeToLiveExpiration;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReplicateRegion(name = RegionNames.SERVICE_RESPONSE_STATUS,ignoreIfExists = true)
@TimeToLiveExpiration(action = "DESTROY",timeout = "60")

public class ServiceResponseStatus implements DataSerializable {

    @Id
    private ServiceKey serviceKey;

    @Indexed
    private String messageId;

    private String originalContainerId;
    private boolean pending;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        serviceKey.toData(dataOutput);
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(originalContainerId);
        dataOutput.writeBoolean(pending);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        serviceKey = new ServiceKey();
        serviceKey.fromData(dataInput);
        messageId = dataInput.readUTF();
        pending = dataInput.readBoolean();

    }
}
