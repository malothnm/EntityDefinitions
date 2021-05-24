package in.nmaloth.entity.customer;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.apache.geode.DataSerializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Region(RegionNames.CUSTOMER_DEF)
public class CustomerDef implements DataSerializable {


    @Id
    private String customerId;


    private AddressType addressType;
    private CustomerType customerType;

    private String customerName;

    private String addressLine1;
    private String addressLine2;

    private String postalCode;
    private String state;
    private String countryCode;


    private Map<CustomerIDType,String> customerIDMap;

    // To Support Multiple Addresses during Authorization

    private String primaryPhoneNumber;
    private String primaryEmail;


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

        dataOutput.write(bitSet.toByteArray());

        dataOutput.writeUTF(customerId);
        addressType.toData(dataOutput);
        customerType.toData(dataOutput);
        dataOutput.writeUTF(customerName);
        dataOutput.writeUTF(addressLine1);
        if(addressLine2 != null){
            dataOutput.writeUTF(addressLine2);
        }

        dataOutput.writeUTF(postalCode);
        dataOutput.writeUTF(state);
        dataOutput.writeUTF(countryCode);

        if(customerIDMap != null){
            dataOutput.writeInt(customerIDMap.size());

            customerIDMap.entrySet()
                    .forEach(customerIDTypeStringEntry -> {
                        try {
                            customerIDTypeStringEntry.getKey().toData(dataOutput);
                            dataOutput.writeUTF(customerIDTypeStringEntry.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        if (primaryPhoneNumber != null){
            dataOutput.writeUTF(primaryPhoneNumber);
        }
        if(primaryEmail != null){
            dataOutput.writeUTF(primaryEmail);
        }
    }

    private void populateBitSet(BitSet bitSet) {

        if(addressLine2 != null){
            bitSet.set(0);
        }
        if(customerIDMap != null){
            bitSet.set(1);
        }
        if(primaryPhoneNumber != null){
            bitSet.set(2);
        }
        if(primaryEmail != null){
            bitSet.set(3);
        }
    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        byte[] bytes = new byte[4];
        dataInput.readFully(bytes);

        BitSet bitSet = BitSet.valueOf(bytes);
        customerId = dataInput.readUTF();
        addressType = AddressType.fromData(dataInput);
        customerType = CustomerType.fromData(dataInput);
        customerName = dataInput.readUTF();
        addressLine1 = dataInput.readUTF();

        if(bitSet.get(0)){
            addressLine2 = dataInput.readUTF();
        }
        postalCode = dataInput.readUTF();
        state = dataInput.readUTF();
        countryCode = dataInput.readUTF();

        if(bitSet.get(1)){
            customerIDMap = new HashMap<>();
            int customerIdMapSize = dataInput.readInt();
            for (int i = 0; i < customerIdMapSize; i ++ ){
                CustomerIDType customerIDType = CustomerIDType.fromData(dataInput);
                String customerId = dataInput.readUTF();
                customerIDMap.put(customerIDType,customerId);
            }
        }

        if (bitSet.get(2)){
            primaryPhoneNumber = dataInput.readUTF();
        }
        if(bitSet.get(3)){
            primaryEmail = dataInput.readUTF();
        }
    }

}
