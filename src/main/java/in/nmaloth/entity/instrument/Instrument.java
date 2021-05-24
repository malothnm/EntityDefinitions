package in.nmaloth.entity.instrument;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.RegionNames;
import in.nmaloth.entity.account.AccountDef;
import in.nmaloth.entity.account.AccountType;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PartitionRegion(name = RegionNames.INSTRUMENT,redundantCopies = 1)
public class Instrument  implements DataSerializable {

    @Id
    private String instrumentNumber;

    private InstrumentType instrumentType;


    private boolean active;

    @Indexed
    private String cardNumber;
    private Set<AccountDef> accountDefSet;
    private String customerNumber;
    private boolean multiCurrency;
    private boolean multipleAccountType;
    private AccountType accountType;

    private String corporateNumber;

    private BlockType blockType;
    private LocalDateTime expiryDate;
    private int org;
    private int product;


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
        dataOutput.writeUTF(instrumentNumber);
        instrumentType.toData(dataOutput);
        dataOutput.writeBoolean(active);
        dataOutput.writeUTF(cardNumber);
        dataOutput.writeInt(accountDefSet.size());
        accountDefSet.forEach(accountDef -> {
            try {
                accountDef.toData(dataOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        dataOutput.writeUTF(customerNumber);
        dataOutput.writeBoolean(multiCurrency);
        dataOutput.writeBoolean(multipleAccountType);

        if(corporateNumber != null){
            dataOutput.writeUTF(corporateNumber);
        }
        if(blockType != null){
            blockType.toData(dataOutput);
        }
        if(expiryDate != null){
            dataOutput.writeUTF(expiryDate.format(DateTimeFormatter.ISO_DATE_TIME));
        }

        if(accountType != null){
            dataOutput.writeUTF(accountType.getAccountType());
        }
        dataOutput.writeInt(org);
        dataOutput.writeInt(product);

    }

    private void populateBitSet(BitSet bitSet) {

        if(corporateNumber != null){
            bitSet.set(0);
        }
        if(blockType != null){
            bitSet.set(1);
        }
        if(expiryDate != null){
            bitSet.set(2);
        }
        if(accountType != null){
            bitSet.set(3);
        }

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);
        BitSet bitSet = BitSet.valueOf(bytes);

        instrumentNumber = dataInput.readUTF();
        instrumentType = InstrumentType.fromData(dataInput);
        active = dataInput.readBoolean();
        cardNumber = dataInput.readUTF();
        int accountDefSetSize = dataInput.readInt();
        accountDefSet = new HashSet<>();
        for (int i = 0; i < accountDefSetSize; i ++ ){
            AccountDef accountDef = new AccountDef();
            accountDef.fromData(dataInput);
            accountDefSet.add(accountDef);
        }
        customerNumber = dataInput.readUTF();
        multiCurrency = dataInput.readBoolean();
        multipleAccountType = dataInput.readBoolean();
        if(bitSet.get(0)){
            corporateNumber = dataInput.readUTF();
        }
        if(bitSet.get(1)){
            blockType = BlockType.fromData(dataInput);
        }
        if(bitSet.get(2)){
            expiryDate = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        }

        if(bitSet.get(3)){
            accountType = AccountType.identify(dataInput.readUTF());
        }
        org = dataInput.readInt();
        product = dataInput.readInt();

    }
}
