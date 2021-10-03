package in.nmaloth.entity.account;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum  BalanceTypes  {

    CURRENT_BALANCE("0"),
    CASH_BALANCE("1"),
    INSTALLMENT_BALANCE("2"),
    INSTALLMENT_CASH("3");

    private String balanceTypes;

    BalanceTypes (String balanceTypes){
        this.balanceTypes = balanceTypes;
    }

    public String getBalanceTypes() {
        return balanceTypes;
    }

    public static BalanceTypes identify(String balanceTypes){
        switch (balanceTypes){
            case "0": {
                return BalanceTypes.CURRENT_BALANCE;
            }
            case "1": {
                return BalanceTypes.CASH_BALANCE;
            }
            case "2" : {
                return BalanceTypes.INSTALLMENT_BALANCE;
            }
            case "3": {
                return BalanceTypes.INSTALLMENT_CASH;
            }
            default: {
                log.error(" Invalid Balance Type : {}",balanceTypes);
                return BalanceTypes.CURRENT_BALANCE;
            }
        }

    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(balanceTypes);
    }

    public static BalanceTypes fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }
}
