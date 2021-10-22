package in.nmaloth.entity.account;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PartitionRegion(name = RegionNames.ACCOUNT_LIMIT,ignoreIfExists = true)
public class AccountAccumValues implements DataSerializable{

    @Id
    private String  accountId;
    private int org;
    private int product;
    private AccountType accountType;
    private BlockType blockType;
    private Map<BalanceTypes,Long> limitsMap;
    private Map<BalanceTypes,AccountBalances> balancesMap;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {


        BitSet bitSet = new BitSet(32);

        populateBitSet(bitSet);

        byte[] bytes = bitSet.toByteArray();

        if(bytes.length < 4){
            byte[] bytesNew = new byte[4];
            System.arraycopy(bytes,0,bytesNew,0,bytes.length);
            bytes = bytesNew;
        }

        dataOutput.write(bytes);
        dataOutput.writeUTF(accountId);
        dataOutput.writeInt(org);
        dataOutput.writeInt(product);
        dataOutput.writeUTF(accountType.getAccountType());
        blockType.toData(dataOutput);
        if(limitsMap != null){
            dataOutput.writeInt(limitsMap.size());
            limitsMap.entrySet()
                    .forEach(balanceTypesLongEntry -> {
                        try {
                            dataOutput.writeUTF(balanceTypesLongEntry.getKey().getBalanceTypes());
                            dataOutput.writeLong(balanceTypesLongEntry.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        if(balancesMap != null){
            dataOutput.writeInt(balancesMap.size());
            balancesMap.entrySet()
                    .forEach(balanceTypesAccountBalancesEntry -> {
                        try {
                            dataOutput.writeUTF(balanceTypesAccountBalancesEntry.getKey().getBalanceTypes());
                            balanceTypesAccountBalancesEntry.getValue().toData(dataOutput);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private void populateBitSet(BitSet bitSet) {

        if(limitsMap != null){
            bitSet.set(0);
        }

        if(balancesMap != null){
            bitSet.set(1);
        }
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);
        BitSet bitSet = BitSet.valueOf(bytes);


        this.accountId = dataInput.readUTF();
        this.org = dataInput.readInt();
        this.product = dataInput.readInt();
        this.accountType = AccountType.identify(dataInput.readUTF());
        this.blockType = BlockType.fromData(dataInput);
        if(bitSet.get(0)){

            int limitMapSize = dataInput.readInt();
            limitsMap = new HashMap<>();
            for (int i = 0 ; i < limitMapSize; i ++ ){
                String balanceTypeString = dataInput.readUTF();
                BalanceTypes balanceTypes = BalanceTypes.identify(balanceTypeString);
                Long limitAmount = dataInput.readLong();
                limitsMap.put(balanceTypes,limitAmount);
            }

        }

        if(bitSet.get(1)){
            int balancesMapSize = dataInput.readInt();
            balancesMap = new HashMap<>();

            for (int i = 0; i < balancesMapSize; i ++  ){
                AccountBalances accountBalances = new AccountBalances();
                String balanceTypeString = dataInput.readUTF();
                BalanceTypes balanceTypes = BalanceTypes.identify(balanceTypeString);
                accountBalances.fromData(dataInput);
                balancesMap.put(balanceTypes,accountBalances);
            }
        }



    }
}
