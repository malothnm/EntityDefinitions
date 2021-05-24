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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PartitionRegion(name = RegionNames.ACCOUNT_BASIC,ignoreIfExists = true)
public class AccountBasic implements DataSerializable {


    @Id
    private String accountId;

    private AccountType accountType;
    private  Integer org;
    private Integer product;
    private BlockType blockType;
    private LocalDateTime dateBlockApplied;
    private BlockType previousBlockType;
    private LocalDateTime datePreviousBLockType;

    private String billingCurrencyCode;

    private String previousAccountNumber;
    private LocalDateTime dateTransfer;

    private String customerNumber;

    private String corporateNumber;


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
        dataOutput.writeUTF(accountType.getAccountType());
        dataOutput.writeInt(org);
        dataOutput.writeInt(product);
        blockType.toData(dataOutput);
        if(dateBlockApplied != null){
            dataOutput.writeUTF(dateBlockApplied.format(DateTimeFormatter.ISO_DATE_TIME));
        }
        if(previousBlockType != null){
            previousBlockType.toData(dataOutput);
        }
        if(datePreviousBLockType != null){
            dataOutput.writeUTF(datePreviousBLockType.format(DateTimeFormatter.ISO_DATE_TIME));
        }
        dataOutput.writeUTF(billingCurrencyCode);
        if(previousAccountNumber != null){
            dataOutput.writeUTF(previousAccountNumber);
        }
        if(dateTransfer != null){
            dataOutput.writeUTF(dateTransfer.format(DateTimeFormatter.ISO_DATE_TIME));
        }
        dataOutput.writeUTF(customerNumber);
        if(corporateNumber != null){
            dataOutput.writeUTF(corporateNumber);
        }

    }

    private void populateBitSet(BitSet bitSet) {

        if(dateBlockApplied != null){
            bitSet.set(0);
        }
        if(previousBlockType != null){
            bitSet.set(1);
        }
        if(datePreviousBLockType != null){
            bitSet.set(2);
        }
        if(previousAccountNumber != null){
            bitSet.set(3);
        }
        if(dateTransfer != null){
            bitSet.set(4);
        }

        if(corporateNumber != null){
            bitSet.set(5);

        }
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);
        BitSet bitSet = BitSet.valueOf(bytes);

        accountId = dataInput.readUTF();
        accountType = AccountType.identify(dataInput.readUTF());
        org = dataInput.readInt();
        product = dataInput.readInt();
        blockType = BlockType.fromData(dataInput);

        if(bitSet.get(0)){
            dateBlockApplied = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        }

        if(bitSet.get(1)){
            previousBlockType = BlockType.fromData(dataInput);
        }
        if(bitSet.get(2)){
            datePreviousBLockType = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        }
        billingCurrencyCode = dataInput.readUTF();
        if(bitSet.get(3)){
            previousAccountNumber = dataInput.readUTF();
        }

        if(bitSet.get(4)){
            dateTransfer = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        }
        customerNumber = dataInput.readUTF();
        if(bitSet.get(5)){
            corporateNumber = dataInput.readUTF();
        }

    }
}
