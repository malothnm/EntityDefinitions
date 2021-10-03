package in.nmaloth.entity.instrument;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;

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
@PartitionRegion(name = RegionNames.INSTRUMENT_CRYPTO,redundantCopies = 1)
public class InstrumentCrypto implements DataSerializable {

    @Id
    private String instrument;
    private Integer cryptoOrg;
    private Integer cryptoProduct;
    private List<CryptoInfo> cryptoInfoList;
    private List<ChipInfo> chipInfoList;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(instrument);
        dataOutput.writeInt(cryptoOrg);
        dataOutput.writeInt(cryptoProduct);
        dataOutput.writeInt(cryptoInfoList.size());
        if(cryptoInfoList.size() > 0){
            cryptoInfoList.forEach(cryptoInfo -> {
                try {
                    cryptoInfo.toData(dataOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        dataOutput.writeInt(chipInfoList.size());
        if(chipInfoList.size() > 0){
            chipInfoList.forEach(chipInfo -> {
                try {
                    chipInfo.toData(dataOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        instrument = dataInput.readUTF();
        cryptoOrg = dataInput.readInt();
        cryptoProduct = dataInput.readInt();
        int length = dataInput.readInt();
        cryptoInfoList = new ArrayList<>();
        for (int i = 0; i < length; i ++ ){
            CryptoInfo cryptoInfo = new CryptoInfo();
            cryptoInfo.fromData(dataInput);
            cryptoInfoList.add(cryptoInfo);

        }
        length = dataInput.readInt();
        chipInfoList = new ArrayList<>();
        for(int i = 0; i < length; i ++ ){
            ChipInfo chipInfo= new ChipInfo();
            chipInfo.fromData(dataInput);
            chipInfoList.add(chipInfo);
        }
    }
}
