package in.nmaloth.entity.account;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTypesTest {

    @Test
    void serialization() throws IOException, ClassNotFoundException {

        BalanceTypes balanceTypes = BalanceTypes.CURRENT_BALANCE;

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serial.txt"));

        balanceTypes.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serial.txt"));

        BalanceTypes balanceTypes1 = BalanceTypes.fromData(dataInput);
        assertEquals(balanceTypes,balanceTypes1);

    }

}