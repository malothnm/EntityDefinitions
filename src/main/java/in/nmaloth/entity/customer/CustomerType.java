package in.nmaloth.entity.customer;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum CustomerType {
    OWNER("O"),
    CO_OWNER("C");

    private String customerType;

    CustomerType(String customerType){
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }


    public static CustomerType identify(String customerType){
        switch (customerType){
            case "O":
                return CustomerType.OWNER;
            case "C":
                return CustomerType.CO_OWNER;
        }
        log.error("Invalid Customer Type {}", customerType);
        throw new InvalidEnumConversion("Invalid Customer Type");
    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(customerType);
    }

    public static CustomerType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }
}
