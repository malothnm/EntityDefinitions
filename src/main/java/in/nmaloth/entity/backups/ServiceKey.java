package in.nmaloth.entity.backups;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ServiceKey implements DataSerializable {

    private String id;
    private String serviceID;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(id);
        dataOutput.writeUTF(serviceID);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        this.id = dataInput.readUTF();
        this.serviceID = dataInput.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceKey that = (ServiceKey) o;
        return id.equals(that.id) && serviceID.equals(that.serviceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceID);
    }
}
