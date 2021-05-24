package in.nmaloth.function.model.output;

import in.nmaloth.payments.constants.ServiceResponse;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountValidationResponse implements DataSerializable  {


    private String serviceId;
    private String messageId;
    private String idNumber;
    private ServiceResponse serviceResponse;
    private Long accountBalance;
    private Long otb;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(serviceId);
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(idNumber);
        dataOutput.writeUTF(serviceResponse.getServiceResponse());
        dataOutput.writeLong(accountBalance);
        dataOutput.writeLong(otb);
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        serviceId = dataInput.readUTF();
        messageId = dataInput.readUTF();
        idNumber = dataInput.readUTF();
        serviceResponse = ServiceResponse.valueOf(dataInput.readUTF());
        accountBalance = dataInput.readLong();
        otb = dataInput.readLong();


    }






}
