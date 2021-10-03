package in.nmaloth.entity.logs;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.card.LimitType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@PartitionRegion(name = RegionNames.AUTH_SNAPSHOT,ignoreIfExists = true)

@Slf4j
public class AuthSnapShot {
    @Id
    private SnapshotKey snapshotKey;

    private LocalDateTime authorizationTime;
    private String responseCode;
    private ReversalStatus reversalStatus;
    private List<LimitType> limitTypes;
    private List<BalanceTypes> balanceTypes;
    private long transactionAmount;
    private long billingAmount;
    private String transactionCurrencyCode;
    private String billingCurrencyCode;
    private int preAuthTime;
    private String authCode;
    private String de007;
    private int traceNumber;

    public void toData(DataOutput dataOutput) throws IOException {
        log.info("Entering the Serialization process");
        BitSet bitSet = new BitSet(32);
        populateBitMap(bitSet);

        byte[] bytes = bitSet.toByteArray();

        if(bytes.length < 4){
            byte[] bytesNew = new byte[4];
            System.arraycopy(bytes,0,bytesNew,0,bytes.length);
            bytes = bytesNew;
        }
        dataOutput.write(bytes);
        snapshotKey.toData(dataOutput);
        dataOutput.writeUTF(authorizationTime.format(DateTimeFormatter.ISO_DATE_TIME));
        dataOutput.writeUTF(responseCode);
        dataOutput.writeUTF(reversalStatus.getReversalStatus());
        if(limitTypes != null){
            dataOutput.writeInt(limitTypes.size());
            for (LimitType limitType: limitTypes) {
                dataOutput.writeUTF(limitType.getLimitType());
            }
        }
        if(balanceTypes != null) {
            dataOutput.writeInt(balanceTypes.size());
            for (BalanceTypes balanceType : balanceTypes) {
                dataOutput.writeUTF(balanceType.getBalanceTypes());
            }
        }
        dataOutput.writeLong(transactionAmount);
        dataOutput.writeLong(billingAmount);
        dataOutput.writeUTF(transactionCurrencyCode);
        dataOutput.writeUTF(billingCurrencyCode);
        dataOutput.writeInt(preAuthTime);
        if(authCode != null){
            dataOutput.writeUTF(authCode);
        }
        dataOutput.writeUTF(de007);
        dataOutput.writeInt(traceNumber);

    }

    private void populateBitMap(BitSet bitSet) {


        if(limitTypes != null){
            bitSet.set(0);
        }
        if(balanceTypes != null){
            bitSet.set(1);
        }
        if(authCode != null){
            bitSet.set(2);
        }
    }

    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);
        BitSet bitSet = BitSet.valueOf(bytes);
        snapshotKey.fromData(dataInput);
        authorizationTime = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        responseCode = dataInput.readUTF();
        reversalStatus = ReversalStatus.identify(dataInput.readUTF());
        if(bitSet.get(0)){
            int length = dataInput.readInt();
            limitTypes = new ArrayList<>();
            for (int i = 0; i < length; i ++){
                limitTypes.add(LimitType.identify(dataInput.readUTF()));
            }
        }
        if(bitSet.get(1)){
            int length = dataInput.readInt();
            balanceTypes = new ArrayList<>();
            for (int i = 0; i < length; i ++ ){
                balanceTypes.add(BalanceTypes.identify(dataInput.readUTF()));
            }
        }
        transactionAmount = dataInput.readLong();
        billingAmount = dataInput.readLong();
        transactionCurrencyCode = dataInput.readUTF();
        billingCurrencyCode = dataInput.readUTF();
        preAuthTime = dataInput.readInt();
        if(bitSet.get(2)){
            authCode = dataInput.readUTF();
        }
        de007 = dataInput.readUTF();
        traceNumber = dataInput.readInt();
    }
}
