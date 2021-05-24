package in.nmaloth.entity.card;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum PeriodicType {

    SINGLE("S"),
    DAILY("D"),
    MONTHLY("M"),
    CYCLE_TO_DATE("C"),
    YEARLY("Y");

    private String periodicType;

    PeriodicType(String periodicType){
        this.periodicType = periodicType;
    }

    public String getPeriodicType() {
        return periodicType;
    }

    public static PeriodicType identify(String periodicType){
        switch (periodicType){
            case "S": {
                return PeriodicType.SINGLE;
            }
            case "D" : {
                return PeriodicType.DAILY;
            }
            case "M": {
                return PeriodicType.MONTHLY;
            }
            case "Y": {
                return PeriodicType.YEARLY;
            }
            default:{
                log.error("Invalid periodic Type ..{}",periodicType);
                throw  new InvalidEnumConversion("Invalid periodic Type");
            }

        }

    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(periodicType);
    }

    public static PeriodicType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }

}
