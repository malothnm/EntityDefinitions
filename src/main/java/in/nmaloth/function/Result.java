package in.nmaloth.function;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result  implements DataSerializable {

    private String serviceId;
    private String declineReason;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(serviceId);
        dataOutput.writeUTF(declineReason);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        this.serviceId = dataInput.readUTF();
        this.declineReason = dataInput.readUTF();
    }
}
