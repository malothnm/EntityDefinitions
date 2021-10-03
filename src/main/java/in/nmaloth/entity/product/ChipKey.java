package in.nmaloth.entity.product;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChipKey  {

    private String chipVersion;
    private Strategy strategy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChipKey chipKey = (ChipKey) o;
        return chipVersion.equals(chipKey.chipVersion) && strategy == chipKey.strategy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chipVersion, strategy);
    }

    //    @Override
//    public void toData(DataOutput dataOutput) throws IOException {
//
//        dataOutput.writeUTF(chipVersion);
//        dataOutput.writeUTF(strategy);
//    }
//
//    @Override
//    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
//        chipVersion = dataInput.readUTF();
//        strategy = dataInput.readUTF();
//    }
}
