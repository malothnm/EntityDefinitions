package in.nmaloth.entity.customer;

import in.nmaloth.entity.account.BalanceTypes;
import in.nmaloth.entity.exception.InvalidEnumConversion;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public enum EmailType {

    PERSONAL("P"),
    WORK("W");

    private String emailType;

    EmailType(String emailType){
        this.emailType = emailType;
    }

    public String getEmailType() {
        return emailType;
    }

    public static EmailType identify(String emailType){
        switch (emailType){
            case "P":
                return EmailType.PERSONAL;
            case "W":
                return EmailType.WORK;
        }
        log.error("Invalid Email Type {}",emailType);
        throw new InvalidEnumConversion("Invalid Email Type");
    }

    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(emailType);
    }

    public static EmailType fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        return identify(dataInput.readUTF());
    }
}
