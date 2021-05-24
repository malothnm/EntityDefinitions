package in.nmaloth.entity.customer;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum CustomerIDType {

    SSN_OR_NATIONAL_ID("0"),
    TAX_ID("1"),
    DRIVERS_LICENCE("2"),
    VOTER_ID("3"),
    PASSPORT_ID("4"),
    CUSTOM_ID_1("5"),
    CUSTOM_ID_2("6"),
    CUSTOM_ID_3("7");

    private String customerIDType;

    CustomerIDType(String customerIDType){
        this.customerIDType = customerIDType;
    }


    public String getCustomerIDType() {
        return customerIDType;
    }

    public static CustomerIDType identify(String customerType) {

        switch (customerType) {
            case "0":
                return CustomerIDType.SSN_OR_NATIONAL_ID;
            case "1":
                return CustomerIDType.TAX_ID;
            case "2":
                return CustomerIDType.DRIVERS_LICENCE;
            case "3":
                return CustomerIDType.VOTER_ID;
            case "4":
                return CustomerIDType.PASSPORT_ID;
            case "5":
                return CustomerIDType.CUSTOM_ID_1;
            case "6":
                return CustomerIDType.CUSTOM_ID_2;
            case "7":
                return CustomerIDType.CUSTOM_ID_3;
        }
        log.error("Invalid CustomerID Type {} ",customerType);
        throw new InvalidEnumConversion("Invalid CustomerID Type");
    }


    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(customerIDType);
    }

    public static CustomerIDType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }

}


