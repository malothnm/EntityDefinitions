package in.nmaloth.function.model.output;

import in.nmaloth.payments.constants.ServiceResponse;
import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationResponse implements DataSerializable {

    private String serviceId;
    private String messageId;
    private String idNumber;
    private List<ServiceResponse> serviceResponseList;
    private Map<String,String> responseFields;





    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(serviceId);
        dataOutput.writeUTF(messageId);
        dataOutput.writeUTF(idNumber);
        dataOutput.writeInt(serviceResponseList.size());
        for(int i = 0; i < serviceResponseList.size(); i++){
            dataOutput.writeUTF(serviceResponseList.get(i).getServiceResponse());

        }
        if(responseFields == null){
            dataOutput.writeInt(0);
        } else {
            dataOutput.writeInt(responseFields.size());

            for ( Map.Entry<String,String>responseFieldEntry : responseFields.entrySet()){
                dataOutput.writeUTF(responseFieldEntry.getKey());
                dataOutput.writeUTF(responseFieldEntry.getValue());
            }
        }

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        serviceId = dataInput.readUTF();
        messageId = dataInput.readUTF();
        idNumber = dataInput.readUTF();
        int size = dataInput.readInt();
        serviceResponseList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            ServiceResponse serviceResponse = ServiceResponse.valueOf(dataInput.readUTF());
            serviceResponseList.add(serviceResponse);
        }
        responseFields = new HashMap<>();
        int length = dataInput.readInt();
        for(int i = 0; i < length; i ++){
            String key = dataInput.readUTF();
            String value = dataInput.readUTF();
            responseFields.put(key,value);
        }
    }
}
