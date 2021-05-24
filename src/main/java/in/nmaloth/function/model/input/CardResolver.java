package in.nmaloth.function.model.input;

import lombok.Getter;
import lombok.Setter;
import org.apache.geode.DataSerializable;
import org.apache.geode.cache.EntryOperation;
import org.apache.geode.cache.PartitionResolver;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


@Getter
@Setter
public class CardResolver implements PartitionResolver<String,Object>, DataSerializable {

    private String key;

    public CardResolver(){
    }

    public CardResolver(String key) {
        this.key = key;
    }

    @Override
    public Object getRoutingObject(EntryOperation<String, Object> entryOperation) {
        return this.key;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(key);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        this.key = dataInput.readUTF();
    }
}
