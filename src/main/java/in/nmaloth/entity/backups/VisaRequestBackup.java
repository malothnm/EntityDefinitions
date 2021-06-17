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
@ReplicateRegion(name = RegionNames.VISA_BACKUP,ignoreIfExists = true)
@TimeToLiveExpiration(action = "DESTROY",timeout = "60")

public class VisaRequestBackup implements DataSerializable {

    @Id
    private String id;
    private boolean messageProcessed;
    private byte[] data;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.id);
        dataOutput.writeBoolean(messageProcessed);
        dataOutput.writeInt(data.length);
        dataOutput.write(data);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        this.id = dataInput.readUTF();
        this.messageProcessed = dataInput.readBoolean();
        int length = dataInput.readInt();
        data = new byte[length];
        for(int i = 0 ; i < length; i ++ ){
            data[i] = dataInput.readByte();
        }
    }
}
