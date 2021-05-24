package in.nmaloth.entity.account;

import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum AccountType {

    SAVINGS("S"),
    CURRENT("CU"),
    LOANS("L"),
    CREDIT("CR"),
    UNIVERSAL("U"),
    PREPAID("P")
    ;

    private String accountType;

    AccountType(String accountType){
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public static AccountType identify(String accountType){

        switch (accountType){
            case "L":
                return AccountType.LOANS;
            case "CR":
                return AccountType.CREDIT;
            case "CU":
                return AccountType.CURRENT;
            case "P":
                return AccountType.PREPAID;
            case "S":
                return AccountType.SAVINGS;
            case "U":
                return AccountType.UNIVERSAL;
        }
        log.error(" Invalid Account Type {} " , accountType);
        throw new InvalidEnumConversion("Invalid Account Type");


    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(accountType);
    }

    public static AccountType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }
}
