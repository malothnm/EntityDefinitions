package in.nmaloth.entity;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum BlockType {

    APPROVE("0"),
    BLOCK_DECLINE("1"),
    BLOCK_PICKUP("2"),
    BLOCK_FRAUD("3"),
    BLOCK_SUSPECTED_FRAUD("4"),
    BLOCK_TEMP("5"),
    VIP_ALWAYS_APPROVE("6");

    private String blockType;

    BlockType(String blockType) {
        this.blockType = blockType;
    }

    public String getBlockType() {
        return blockType;
    }

    public static BlockType identify(String blockType) {
        switch (blockType) {
            case "0": {
                return BlockType.APPROVE;
            }
            case "1": {
                return BlockType.BLOCK_DECLINE;
            }
            case "2": {
                return BlockType.BLOCK_PICKUP;
            }
            case "3": {
                return BlockType.BLOCK_FRAUD;
            }
            case "4": {
                return BlockType.BLOCK_SUSPECTED_FRAUD;
            }
            case "5": {
                return BlockType.BLOCK_TEMP;
            }
            case "6": {
                return BlockType.VIP_ALWAYS_APPROVE;
            }
            default: {
                log.error("Invalid Block Type requested...{}", blockType);
                throw new InvalidEnumConversion("Invalid Block Type requested");
            }
        }
    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(blockType);
    }

    public static BlockType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }
}
