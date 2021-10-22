package in.nmaloth.function.model.output;

import in.nmaloth.entity.account.AccountType;
import in.nmaloth.payments.constants.International;
import in.nmaloth.payments.constants.ServiceResponse;
import lombok.*;
import org.apache.geode.DataSerializable;

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

public class AccountValidationResponse implements DataSerializable  {


    private String serviceId;
    private String messageId;
    private String idNumber;
    private List<ServiceResponse> serviceResponseList;
    private Long accountBalance;
    private Long otb;
    private Long creditLimit;
    private AccountType accountType;
    private International international;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(serviceId);
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(idNumber);
        dataOutput.writeInt(serviceResponseList.size());
        for(int i = 0; i < serviceResponseList.size(); i++){
            dataOutput.writeUTF(serviceResponseList.get(i).getServiceResponse());
        }
//        dataOutput.writeUTF(serviceResponse.getServiceResponse());
        dataOutput.writeLong(accountBalance);
        dataOutput.writeLong(otb);
        dataOutput.writeLong(creditLimit);
        dataOutput.writeUTF(accountType.getAccountType());
        dataOutput.writeUTF(international.getInternational());
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        serviceId = dataInput.readUTF();
        messageId = dataInput.readUTF();
        idNumber = dataInput.readUTF();
        serviceResponseList = new ArrayList<>();
        int size = dataInput.readInt();
        for(int i = 0; i < size; i++){
            ServiceResponse serviceResponse = ServiceResponse.valueOf(dataInput.readUTF());
            serviceResponseList.add(serviceResponse);
        }
//        serviceResponse = ServiceResponse.valueOf(dataInput.readUTF());
        accountBalance = dataInput.readLong();
        otb = dataInput.readLong();
        creditLimit = dataInput.readLong();
        accountType = AccountType.identify(dataInput.readUTF());
        international = International.identify(dataInput.readUTF());

    }






}
