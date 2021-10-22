package in.nmaloth.function.model.input;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInputList implements DataSerializable {

    private List<AccountInput> accountInputList;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeInt(accountInputList.size());
        for(AccountInput accountInput: accountInputList){
            accountInput.toData(dataOutput);
        }

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        int size = dataInput.readInt();
        accountInputList = new ArrayList<>();
        for(int i = 0; i < size; i ++){
            AccountInput accountInput = new AccountInput();
            accountInput.fromData(dataInput);
            accountInputList.add(accountInput);
        }
    }
}
