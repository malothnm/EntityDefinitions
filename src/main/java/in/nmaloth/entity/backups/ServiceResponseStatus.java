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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ReplicateRegion(name = RegionNames.SERVICE_RESPONSE_STATUS,ignoreIfExists = true)
@TimeToLiveExpiration(action = "DESTROY",timeout = "60")

public class ServiceResponseStatus implements DataSerializable {

    @Id
    private String messageId;

    private String microServiceId;
    private boolean complete;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(microServiceId);
        dataOutput.writeBoolean(complete);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        messageId = dataInput.readUTF();
        microServiceId = dataInput.readUTF();
        complete = dataInput.readBoolean();
    }
}
