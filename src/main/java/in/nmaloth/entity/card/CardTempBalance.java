package in.nmaloth.entity.card;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.config.annotation.EnableExpiration;
import org.springframework.data.gemfire.expiration.TimeToLiveExpiration;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

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
@ReplicateRegion(name = RegionNames.CARD_TEMP_BALANCE,ignoreIfExists = true)
@TimeToLiveExpiration(action = "DESTROY",timeout = "60")
public class CardTempBalance  implements DataSerializable {

    @Id
    private String id;

    @Indexed
    private String cardNumber;
    private Long amount;
    private List<LimitType> limitTypes;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(id);
        dataOutput.writeUTF(cardNumber);
        dataOutput.writeLong(amount);
        dataOutput.writeInt(limitTypes.size());
        if(limitTypes.size() > 0 ){
            limitTypes.forEach(limitType -> {
                try {
                    limitType.toData(dataOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        id = dataInput.readUTF();
        cardNumber = dataInput.readUTF();
        amount = dataInput.readLong();

        int limitTypeSize = dataInput.readInt();
        limitTypes = new ArrayList<>();

        for( int i = 0; i < limitTypeSize ; i ++ ){
            limitTypes.add(LimitType.fromData(dataInput));
        }
    }
}
