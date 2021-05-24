package in.nmaloth.entity.instrument;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.account.AccountDef;
import in.nmaloth.entity.account.AccountType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentTest {

    @Test
    void serializeData() throws IOException, ClassNotFoundException {

        String instrumentNumber = UUID.randomUUID().toString().replace("-","");
        String customerNumber =  UUID.randomUUID().toString().replace("-","");
        String cardNumber =  UUID.randomUUID().toString().replace("-","");

        AccountDef accountDef1 = AccountDef.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-",""))
                .accountType(AccountType.CREDIT)
                .billingCurrencyCode("USD")
                .build();

        AccountDef accountDef2 = AccountDef.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-",""))
                .accountType(AccountType.SAVINGS)
                .billingCurrencyCode("MXN")
                .build();
        Set<AccountDef> accountDefSet = new HashSet<>();
        accountDefSet.add(accountDef1);
        accountDefSet.add(accountDef2);

        Instrument instrument = createInstrument(accountDefSet,instrumentNumber,cardNumber,customerNumber);

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/instrument.txt"));

        instrument.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/instrument.txt"));

        Instrument instrument1 = new Instrument();
        instrument1.fromData(dataInput);

        assertAll(
                ()-> assertEquals(instrument.getCardNumber(),instrument1.getCardNumber()),
                ()-> assertEquals(instrument.getInstrumentNumber(),instrument1.getInstrumentNumber()),
                ()-> assertEquals(instrument.getCustomerNumber(),instrument1.getCustomerNumber()),
                ()-> assertEquals(2,instrument1.getAccountDefSet().size()),
                ()-> assertEquals(instrument.getInstrumentType(),instrument1.getInstrumentType()),
                ()-> assertEquals(instrument.getBlockType(),instrument1.getBlockType()),
                ()-> assertEquals(instrument.getExpiryDate(),instrument1.getExpiryDate()),
                ()-> assertEquals(instrument.getOrg(),instrument1.getOrg()),
                ()-> assertEquals(instrument.getProduct(),instrument1.getProduct()),
                ()-> assertEquals(instrument.isActive(),instrument1.isActive()),
                ()-> assertEquals(instrument.isMultiCurrency(),instrument1.isMultiCurrency()),
                ()-> assertEquals(instrument.isMultipleAccountType(),instrument1.isMultipleAccountType()),
                ()-> assertNull(instrument1.getAccountType()),
                ()-> assertNull(instrument1.getCorporateNumber())


                );
    }

    @Test
    void serializeData1() throws IOException, ClassNotFoundException {

        String instrumentNumber = UUID.randomUUID().toString().replace("-","");
        String customerNumber =  UUID.randomUUID().toString().replace("-","");
        String cardNumber =  UUID.randomUUID().toString().replace("-","");

        AccountDef accountDef1 = AccountDef.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-",""))
                .accountType(AccountType.CREDIT)
                .billingCurrencyCode("USD")
                .build();

        AccountDef accountDef2 = AccountDef.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-",""))
                .accountType(AccountType.SAVINGS)
                .billingCurrencyCode("MXN")
                .build();
        Set<AccountDef> accountDefSet = new HashSet<>();
        accountDefSet.add(accountDef1);
        accountDefSet.add(accountDef2);

        Instrument instrument = createInstrument(accountDefSet,instrumentNumber,cardNumber,customerNumber);

        instrument.setMultiCurrency(true);
        instrument.setAccountType(AccountType.CREDIT);
        instrument.setCorporateNumber(UUID.randomUUID().toString().replace("-",""));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/instrument.txt"));

        instrument.toData(dataOutput);


        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/instrument.txt"));

        Instrument instrument1 = new Instrument();
        instrument1.fromData(dataInput);

        assertAll(
                ()-> assertEquals(instrument.getCardNumber(),instrument1.getCardNumber()),
                ()-> assertEquals(instrument.getInstrumentNumber(),instrument1.getInstrumentNumber()),
                ()-> assertEquals(instrument.getCustomerNumber(),instrument1.getCustomerNumber()),
                ()-> assertEquals(2,instrument1.getAccountDefSet().size()),
                ()-> assertEquals(instrument.getInstrumentType(),instrument1.getInstrumentType()),
                ()-> assertEquals(instrument.getBlockType(),instrument1.getBlockType()),
                ()-> assertEquals(instrument.getExpiryDate(),instrument1.getExpiryDate()),
                ()-> assertEquals(instrument.getOrg(),instrument1.getOrg()),
                ()-> assertEquals(instrument.getProduct(),instrument1.getProduct()),
                ()-> assertEquals(instrument.isActive(),instrument1.isActive()),
                ()-> assertEquals(instrument.isMultiCurrency(),instrument1.isMultiCurrency()),
                ()-> assertEquals(instrument.isMultipleAccountType(),instrument1.isMultipleAccountType()),
                ()-> assertEquals(instrument.getAccountType(),instrument1.getAccountType()),
                ()-> assertEquals(instrument.getCorporateNumber(),instrument1.getCorporateNumber())


        );
    }

    private Instrument createInstrument(Set<AccountDef> accountDefSet,String instrumentNumber,String cardNumber,String customerNumber){

        return         Instrument.builder()
                .blockType(BlockType.APPROVE)
                .accountDefSet(accountDefSet)
                .cardNumber(cardNumber)
                .customerNumber(customerNumber)
                .expiryDate(LocalDateTime.of(2021,01,01,01,01,01))
                .active(true)
                .instrumentNumber(instrumentNumber)
                .multiCurrency(false)
                .multipleAccountType(true)
                .instrumentType(InstrumentType.PLASTIC_DEBIT)
                .org(1)
                .product(201)
                .build();
    }

}