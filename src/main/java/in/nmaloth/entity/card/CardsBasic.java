package in.nmaloth.entity.card;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.RegionNames;
import in.nmaloth.payments.constants.EntryMode;
import in.nmaloth.payments.constants.PurchaseTypes;
import in.nmaloth.payments.constants.TerminalType;
import in.nmaloth.payments.constants.TransactionType;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@PartitionRegion(name = RegionNames.CARDS_BASIC)

public class CardsBasic implements DataSerializable {

    @Id
    private String cardId;

    private Integer product;
    private Integer org;
    private CardStatus cardStatus;
    private CardHolderType cardholderType;
    private BlockType blockType;
    private Integer waiverDaysActivation;
    private Integer cardReturnNumber;


    private List<Plastic> plasticList;

    private Map<TransactionType,Boolean> blockTransactionType;
    private Map<TerminalType,Boolean> blockTerminalType;
    private Map<PurchaseTypes,Boolean> blockPurchaseTypes;
    private Boolean blockCashBack;
    private Boolean blockInstallments;
    private Map<EntryMode,Boolean> blockEntryMode;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardsBasic)) return false;
        CardsBasic that = (CardsBasic) o;
        return getCardId().equals(that.getCardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId());
    }

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
        dataOutput.writeUTF(cardStatus.getCardStatus());
        dataOutput.writeUTF(cardholderType.getCardHolderType());
        dataOutput.writeUTF(blockType.getBlockType());

        if(waiverDaysActivation != null){
            dataOutput.writeInt(waiverDaysActivation);
        }
        if(cardReturnNumber != null){
            dataOutput.writeInt(cardReturnNumber);
        }


        if(plasticList != null){
            dataOutput.writeInt(plasticList.size());
            plasticList.forEach(plastic -> {
                try {
                    plastic.toData(dataOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if(blockTransactionType != null){
            dataOutput.writeInt(blockTransactionType.size());
            blockTransactionType.entrySet()
                    .forEach(transactionTypeBooleanEntry -> {
                        try {
                            transactionTypeBooleanEntry.getKey().toData(dataOutput);
                            dataOutput.writeBoolean(transactionTypeBooleanEntry.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
        }

        if(blockTerminalType != null){
            dataOutput.writeInt(blockTerminalType.size());
            blockTerminalType.entrySet()
                    .forEach(terminalTypeBooleanEntry -> {
                        try {
                            terminalTypeBooleanEntry.getKey().toData(dataOutput);
                            dataOutput.writeBoolean(terminalTypeBooleanEntry.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
        }

        if(blockPurchaseTypes != null){
            dataOutput.writeInt(blockPurchaseTypes.size());
            blockPurchaseTypes.entrySet()
                    .forEach(purchaseTypesBooleanEntry -> {
                        try {
                            purchaseTypesBooleanEntry.getKey().toData(dataOutput);
                            dataOutput.writeBoolean(purchaseTypesBooleanEntry.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
        }

        if(blockCashBack != null){
            dataOutput.writeBoolean(blockCashBack);
        }

        if(blockInstallments != null){
            dataOutput.writeBoolean(blockInstallments);
        }

        if(blockEntryMode != null){
            dataOutput.writeInt(blockEntryMode.size());
            blockEntryMode.entrySet()
                    .forEach(entryModeBooleanEntry -> {
                        try {
                            entryModeBooleanEntry.getKey().toData(dataOutput);
                            dataOutput.writeBoolean(entryModeBooleanEntry.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
        }

    }


    private void populateBitSet(BitSet bitSet) {



        if(waiverDaysActivation  != null){
            bitSet.set(0);
        }
        if(cardReturnNumber != null){
            bitSet.set(1);
        }

        if(plasticList != null){
            bitSet.set(2);
        }

        if( blockTransactionType != null){
            bitSet.set(3);
        }
        if(blockTerminalType != null){
            bitSet.set(4);
        }
        if(blockPurchaseTypes != null){
            bitSet.set(5);
        }
        if(blockCashBack != null){
            bitSet.set(6);
        }
        if(blockInstallments != null){
            bitSet.set(7);
        }
        if(blockEntryMode != null){
            bitSet.set(8);
        }
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);

        BitSet bitSet = BitSet.valueOf(bytes);

        cardId = dataInput.readUTF();
        org = dataInput.readInt();
        product = dataInput.readInt();
        cardStatus = CardStatus.identify(dataInput.readUTF());
        cardholderType = CardHolderType.identify(dataInput.readUTF());
        blockType = BlockType.identify(dataInput.readUTF());


        if(bitSet.get(0)){
            waiverDaysActivation = dataInput.readInt();
        }

        if(bitSet.get(1)){
            cardReturnNumber = dataInput.readInt();
        }

        if(bitSet.get(2)){
            int plasticListSize = dataInput.readInt();
            plasticList = new ArrayList<>();
            for( int i = 0; i < plasticListSize; i ++){
                Plastic plastic = new Plastic();
                plastic.fromData(dataInput);
                plasticList.add(plastic);
            }
        }

        if(bitSet.get(3)){
            int blockTransactionTypeSize = dataInput.readInt();
            blockTransactionType = new HashMap<>();
            for (int i = 0; i < blockTransactionTypeSize; i ++ ){
                TransactionType transactionType = TransactionType.fromData(dataInput);
                Boolean block = dataInput.readBoolean();
                blockTransactionType.put(transactionType,block);

            }
        }

        if(bitSet.get(4)){
            int blockTerminalTypeSize = dataInput.readInt();
            blockTerminalType = new HashMap<>();
            for (int i = 0; i < blockTerminalTypeSize; i ++ ){
                TerminalType terminalType = TerminalType.fromData(dataInput);
                Boolean block = dataInput.readBoolean();
                blockTerminalType.put(terminalType,block);

            }
        }

        if(bitSet.get(5)){
            int blockPurchaseTypesSize = dataInput.readInt();
            blockPurchaseTypes = new HashMap<>();
            for (int i = 0; i < blockPurchaseTypesSize; i ++ ){
                PurchaseTypes purchaseTypes = PurchaseTypes.fromData(dataInput);
                Boolean block = dataInput.readBoolean();
                blockPurchaseTypes.put(purchaseTypes,block);

            }
        }

        if(bitSet.get(6)){
            blockCashBack = dataInput.readBoolean();
        }

        if(bitSet.get(7)){
            blockInstallments = dataInput.readBoolean();
        }

        if(bitSet.get(8)){
            int entryModeBlockSize = dataInput.readInt();
            blockEntryMode = new HashMap<>();
            for (int i = 0; i < entryModeBlockSize; i ++ ){
                EntryMode entryMode = EntryMode.fromData(dataInput);
                Boolean block = dataInput.readBoolean();
                blockEntryMode.put(entryMode,block);

            }
        }
    }
}
