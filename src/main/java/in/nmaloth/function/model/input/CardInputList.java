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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardInputList implements DataSerializable {

    private List<CardsInput> cardsInputList;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeInt(cardsInputList.size());
        for(CardsInput cardsInput: cardsInputList){
            cardsInput.toData(dataOutput);
        }

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        int size = dataInput.readInt();
        cardsInputList = new ArrayList<>();
        for(int i = 0; i < size; i ++ ){
            CardsInput cardsInput = new CardsInput();
            cardsInput.fromData(dataInput);
            cardsInputList.add(cardsInput);
        }
    }
}
