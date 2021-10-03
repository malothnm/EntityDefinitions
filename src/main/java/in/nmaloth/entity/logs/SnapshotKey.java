package in.nmaloth.entity.logs;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapshotKey implements DataSerializable {

    private String instrument;
    private String keyExtension;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(instrument);
        dataOutput.writeUTF(keyExtension);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        instrument = dataInput.readUTF();
        keyExtension = dataInput.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SnapshotKey)) return false;
        SnapshotKey that = (SnapshotKey) o;
        return getInstrument().equals(that.getInstrument()) && getKeyExtension().equals(that.getKeyExtension());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstrument());
    }
}
