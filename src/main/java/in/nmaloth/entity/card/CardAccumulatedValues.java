package in.nmaloth.entity.card;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PartitionRegion(name = RegionNames.CARD_LIMIT,collocatedWith = RegionNames.CARDS_BASIC)
public class CardAccumulatedValues implements DataSerializable {

    @Id
    private String cardId;


    private Integer org;
    private Integer product;

    private LocalDateTime lastUpdatedDateTime;

    private Map<PeriodicType, Map<LimitType,PeriodicCardAmount>>  periodicTypePeriodicCardLimitMap;

    private Map<PeriodicType, Map<LimitType,PeriodicCardAmount>> periodicCardAccumulatedValueMap;


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
        dataOutput.writeUTF(cardId);
        dataOutput.writeInt(org);
        dataOutput.writeInt(product);
        dataOutput.writeUTF(lastUpdatedDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        if(periodicTypePeriodicCardLimitMap != null){

            dataOutput.writeInt(periodicTypePeriodicCardLimitMap.size());
            periodicTypePeriodicCardLimitMap.entrySet()
                    .forEach(periodicTypeMapEntry -> {

                        try {
                            dataOutput.writeUTF(periodicTypeMapEntry.getKey().getPeriodicType());
                            dataOutput.writeInt(periodicTypeMapEntry.getValue().size());
                            serializeMap(periodicTypeMapEntry.getValue(),dataOutput);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } );
        }



        if(periodicCardAccumulatedValueMap != null){
            dataOutput.writeInt(periodicCardAccumulatedValueMap.size());
            periodicCardAccumulatedValueMap.entrySet()
                    .forEach(periodicTypeMapEntry -> {
                        try {
                            dataOutput.writeUTF(periodicTypeMapEntry.getKey().getPeriodicType());
                            dataOutput.writeInt(periodicTypeMapEntry.getValue().size());
                            serializeMap(periodicTypeMapEntry.getValue(),dataOutput);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

    }

    private void populateBitSet(BitSet bitSet) {

        if(periodicTypePeriodicCardLimitMap != null){
            bitSet.set(0);
        }

        if(periodicCardAccumulatedValueMap != null){
            bitSet.set(1);
        }
    }

    private void serializeMap(Map<LimitType, PeriodicCardAmount> periodicCardAmountMap, DataOutput dataOutput) {

        periodicCardAmountMap.entrySet()
                .forEach(limitTypePeriodicCardAmountEntry -> {

                    try {
                        dataOutput.writeUTF(limitTypePeriodicCardAmountEntry.getKey().getLimitType());
                        limitTypePeriodicCardAmountEntry.getValue().toData(dataOutput);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {


        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);
        BitSet bitSet = BitSet.valueOf(bytes);


        cardId = dataInput.readUTF();
        org = dataInput.readInt();
        product = dataInput.readInt();
        lastUpdatedDateTime = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);

        if(bitSet.get(0)){
            int periodicLimitMapSize = dataInput.readInt();

            periodicTypePeriodicCardLimitMap = new HashMap<>();
            updatePeriodicLimitMap(periodicTypePeriodicCardLimitMap,periodicLimitMapSize,dataInput);
        }
        if(bitSet.get(1)){
            int periodicAmountMapSize = dataInput.readInt();
            periodicCardAccumulatedValueMap = new HashMap<>();
            updatePeriodicLimitMap(periodicCardAccumulatedValueMap,periodicAmountMapSize,dataInput);

        }
    }

    private void updatePeriodicLimitMap(Map<PeriodicType,Map<LimitType,PeriodicCardAmount>> periodicCardMap,
                                      int periodicAmountMapSize, DataInput dataInput  ) throws IOException, ClassNotFoundException {
        for(int i = 0; i < periodicAmountMapSize; i ++ ){

            PeriodicType periodicType = PeriodicType.identify(dataInput.readUTF());

            int limitMapSize = dataInput.readInt();
            Map<LimitType,PeriodicCardAmount> periodicCardAmountMap = new HashMap<>();
            for (int j = 0; j < limitMapSize; j ++ ){
                LimitType limitType = LimitType.identify(dataInput.readUTF());
                PeriodicCardAmount periodicCardAmount = new PeriodicCardAmount();
                periodicCardAmount.fromData(dataInput);
                periodicCardAmountMap.put(limitType,periodicCardAmount);
            }
            periodicCardMap.put(periodicType,periodicCardAmountMap);
        }
    }
}
