package in.nmaloth.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChipKey implements DataSerializable {

    private String chipVersion;
    private String strategy;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(chipVersion);
        dataOutput.writeUTF(strategy);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        chipVersion = dataInput.readUTF();
        strategy = dataInput.readUTF();
    }
}
