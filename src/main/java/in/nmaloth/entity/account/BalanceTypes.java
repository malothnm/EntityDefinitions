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
    INSTALLMENT_CASH("3"),
    INTERNATIONAL("4"),
    INTERNATIONAL_CASH("5"),
    INTERNATIONAL_INSTALLMENT("6"),
    INTERNATIONAL_CASH_INSTALLMENT("7");

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
            case "4": {
                return BalanceTypes.INTERNATIONAL;
            }
            case "5": {
                return BalanceTypes.INTERNATIONAL_CASH;
            }
            case "6": {
                return BalanceTypes.INTERNATIONAL_INSTALLMENT;
            }
            case "7": {
                return BalanceTypes.INTERNATIONAL_CASH_INSTALLMENT;
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
