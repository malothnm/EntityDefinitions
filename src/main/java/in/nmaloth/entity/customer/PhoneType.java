package in.nmaloth.entity.customer;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum  PhoneType {

    PERSONAL_LAND_LINE("PL"),
    PERSONAL_MOBILE("PM"),
    WORK_PHONE_LAND_LINE("WL"),
    WORK_PHONE_MOBILE("WM"),
    ADDITIONAL_PHONE("AP");


    private String phoneType;

    PhoneType(String phoneType){
        this.phoneType = phoneType;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public static PhoneType identify(String phoneType){
        switch (phoneType){
            case "PL" :
                return PhoneType.PERSONAL_LAND_LINE;
            case "PM":
                return PhoneType.PERSONAL_MOBILE;
            case "WM":
                return PhoneType.WORK_PHONE_MOBILE;
            case "WL":
                return PhoneType.WORK_PHONE_LAND_LINE;
            case "AP":
                return PhoneType.ADDITIONAL_PHONE;
        }

        log.error("Invalid Phone Type {} ", phoneType);
        throw new InvalidEnumConversion("Invalid Phone Type");
    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phoneType);
    }

    public static PhoneType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }
}
