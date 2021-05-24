package in.nmaloth.entity.card;

import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.BitSet;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plastic  implements DataSerializable {

    private String plasticId;
    private LocalDate expiryDate;
    private Boolean cardActivated;
    private LocalDateTime cardActivatedDate;
    private LocalDateTime datePlasticIssued;
    private LocalDate dateCardValidFrom;
    private Duration activationWaiveDuration;
    private Boolean dynamicCVV;
    private CardAction pendingCardAction;
    private CardAction cardAction;


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

        dataOutput.writeUTF(plasticId);
        dataOutput.writeUTF(expiryDate.format(DateTimeFormatter.ISO_DATE));
        dataOutput.writeBoolean(cardActivated);
        if(cardActivatedDate != null){
            dataOutput.writeUTF(cardActivatedDate.format(DateTimeFormatter.ISO_DATE_TIME));
        }
        if(datePlasticIssued != null){
            dataOutput.writeUTF(datePlasticIssued.format(DateTimeFormatter.ISO_DATE_TIME));
        }
        if(dateCardValidFrom != null){
            dataOutput.writeUTF(dateCardValidFrom.format(DateTimeFormatter.ISO_DATE));
        }
        if(activationWaiveDuration != null){
            dataOutput.writeLong(activationWaiveDuration.toDays());
        }
        if(dynamicCVV != null){
            dataOutput.writeBoolean(dynamicCVV);
        }
        pendingCardAction.toData(dataOutput);
        cardAction.toData(dataOutput);

    }

    private void populateBitSet(BitSet bitSet) {

        if(cardActivatedDate != null){
            bitSet.set(0);
        }
        if(datePlasticIssued != null){
            bitSet.set(1);
        }
        if(dateCardValidFrom != null){
            bitSet.set(2);
        }
        if(activationWaiveDuration != null){
            bitSet.set(3);
        }
        if(dynamicCVV != null){
            bitSet.set(4);
        }

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);

        BitSet bitSet = BitSet.valueOf(bytes);

        plasticId = dataInput.readUTF();
        expiryDate = LocalDate.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE);
        cardActivated = dataInput.readBoolean();
        if(bitSet.get(0)){
            cardActivatedDate = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        }
        if(bitSet.get(1)){
            datePlasticIssued = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);
        }
        if(bitSet.get(2)){
            dateCardValidFrom = LocalDate.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE);
        }
        if(bitSet.get(3)){
            activationWaiveDuration = Duration.ofDays(dataInput.readLong());
        }
        if(bitSet.get(4)){
            dynamicCVV = dataInput.readBoolean();
        }
        pendingCardAction = CardAction.fromData(dataInput);
        cardAction = CardAction.fromData(dataInput);

    }
}
