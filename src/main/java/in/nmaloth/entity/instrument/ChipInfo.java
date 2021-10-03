package in.nmaloth.entity.instrument;

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
public class ChipInfo implements DataSerializable {

    private String chipVersion;
    private String iadFormat;
    private int chipSeq;
    private int atc;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(chipVersion);
        dataOutput.writeUTF(iadFormat);
        dataOutput.writeInt(chipSeq);
        dataOutput.writeInt(atc);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        chipVersion = dataInput.readUTF();
        iadFormat = dataInput.readUTF();
        chipSeq = dataInput.readInt();
        atc = dataInput.readInt();
    }
}
