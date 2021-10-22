package in.nmaloth.entity.account;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.entity.card.CardAccumulatedValues;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.expiration.TimeToLiveExpiration;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PartitionRegion(name = RegionNames.ACCT_TEMP_BALANCE,ignoreIfExists = true,collocatedWith =  RegionNames.ACCOUNT_LIMIT)
public class AccountTempBalance implements DataSerializable {

    @Id
    private String id;

    @Indexed
    private String accountNumber;

    private long amount;
    private List<BalanceTypes> balanceTypesList;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(id);
        dataOutput.writeUTF(accountNumber);
        dataOutput.writeLong(amount);
        dataOutput.writeInt(balanceTypesList.size());
        if(balanceTypesList.size() > 0 ){
            balanceTypesList.forEach(balanceType -> {
                try {
                    balanceType.toData(dataOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        id = dataInput.readUTF();
        accountNumber = dataInput.readUTF();
        amount = dataInput.readLong();

        int balanceTypeListSize = dataInput.readInt();
        balanceTypesList = new ArrayList<>();

        for( int i = 0; i < balanceTypeListSize ; i ++ ){
            balanceTypesList.add(BalanceTypes.fromData(dataInput));
        }

    }
}
