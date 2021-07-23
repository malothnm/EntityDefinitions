package in.nmaloth.entity.instrument;


import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CryptoInfo implements DataSerializable {

    private String serviceCode;
    private PinBlockFormat pinBlockFormat;
    private LocalDate expiryDate;
    private int pinOffset;
    private boolean dynamicCvv;
    private String chipVersion;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {



        dataOutput.writeUTF(serviceCode);
        dataOutput.writeUTF(pinBlockFormat.getPinBlockFormat());
        dataOutput.writeUTF(expiryDate.format(DateTimeFormatter.ISO_DATE));
        dataOutput.writeInt(pinOffset);
        dataOutput.writeBoolean(dynamicCvv);



    }



    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {


        serviceCode = dataInput.readUTF();
        pinBlockFormat = PinBlockFormat.identify(dataInput.readUTF());
        expiryDate = LocalDate.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE);
        pinOffset = dataInput.readInt();
        dynamicCvv = dataInput.readBoolean();

    }


}
