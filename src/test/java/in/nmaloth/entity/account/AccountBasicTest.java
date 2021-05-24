package in.nmaloth.entity.account;

import in.nmaloth.entity.BlockType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountBasicTest {

    @Test
    void testAccountBasic() throws IOException, ClassNotFoundException {

        AccountBasic accountBasic = createAccountBasic(true,null);

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAcctBasic.txt"));

        accountBasic.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAcctBasic.txt"));


        AccountBasic accountBasic1 = new AccountBasic();
        accountBasic1.fromData(dataInput);


        assertAll(

                ()-> assertEquals(accountBasic.getAccountId(),accountBasic1.getAccountId()),
                ()-> assertEquals(accountBasic.getAccountType(),accountBasic1.getAccountType()),
                ()-> assertEquals(accountBasic.getOrg(),accountBasic1.getOrg()),
                ()-> assertEquals(accountBasic.getProduct(),accountBasic1.getProduct()),
                ()-> assertEquals(accountBasic.getBlockType(),accountBasic1.getBlockType()),
                ()-> assertTrue(accountBasic.getDateBlockApplied().isEqual(accountBasic1.getDateBlockApplied())),
                ()->assertEquals(accountBasic.getPreviousBlockType(),accountBasic1.getPreviousBlockType()),
                ()-> assertTrue(accountBasic.getDatePreviousBLockType().isEqual(accountBasic1.getDatePreviousBLockType())),
                ()-> assertEquals(accountBasic.getBillingCurrencyCode(),accountBasic1.getBillingCurrencyCode()),
                ()-> assertEquals(accountBasic.getPreviousAccountNumber(),accountBasic1.getPreviousAccountNumber()),
                ()-> assertTrue(accountBasic.getDateTransfer().isEqual(accountBasic1.getDateTransfer())),
                ()-> assertEquals(accountBasic.getCustomerNumber(),accountBasic1.getCustomerNumber()),
                ()-> assertEquals(accountBasic.getCorporateNumber(),accountBasic1.getCorporateNumber())

        );
    }

    @Test
    void testAccountBasic1() throws IOException, ClassNotFoundException {

        AccountBasic accountBasic = createAccountBasic(false, Arrays.asList(1,3,5));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAcctBasic.txt"));

        accountBasic.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAcctBasic.txt"));


        AccountBasic accountBasic1 = new AccountBasic();
        accountBasic1.fromData(dataInput);


        assertAll(

                ()-> assertEquals(accountBasic.getAccountId(),accountBasic1.getAccountId()),
                ()-> assertEquals(accountBasic.getAccountType(),accountBasic1.getAccountType()),
                ()-> assertEquals(accountBasic.getOrg(),accountBasic1.getOrg()),
                ()-> assertEquals(accountBasic.getProduct(),accountBasic1.getProduct()),
                ()-> assertEquals(accountBasic.getBlockType(),accountBasic1.getBlockType()),
                ()-> assertNull(accountBasic1.getDateBlockApplied()),

                ()->assertEquals(accountBasic.getPreviousBlockType(),accountBasic1.getPreviousBlockType()),
                ()-> assertNull(accountBasic1.getDatePreviousBLockType()),

                ()-> assertEquals(accountBasic.getBillingCurrencyCode(),accountBasic1.getBillingCurrencyCode()),
                ()-> assertNull(accountBasic1.getPreviousAccountNumber()),
                ()-> assertTrue(accountBasic.getDateTransfer().isEqual(accountBasic1.getDateTransfer())),

                ()-> assertEquals(accountBasic.getCustomerNumber(),accountBasic1.getCustomerNumber()),
                ()-> assertEquals(accountBasic.getCorporateNumber(),accountBasic1.getCorporateNumber())



        );
    }

    @Test
    void testAccountBasic2() throws IOException, ClassNotFoundException {

        AccountBasic accountBasic = createAccountBasic(false,Arrays.asList(0,2,4));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAcctBasic.txt"));

        accountBasic.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAcctBasic.txt"));


        AccountBasic accountBasic1 = new AccountBasic();
        accountBasic1.fromData(dataInput);


        assertAll(

                ()-> assertEquals(accountBasic.getAccountId(),accountBasic1.getAccountId()),
                ()-> assertEquals(accountBasic.getAccountType(),accountBasic1.getAccountType()),
                ()-> assertEquals(accountBasic.getOrg(),accountBasic1.getOrg()),
                ()-> assertEquals(accountBasic.getProduct(),accountBasic1.getProduct()),
                ()-> assertEquals(accountBasic.getBlockType(),accountBasic1.getBlockType()),
                ()->assertNull(accountBasic1.getPreviousBlockType()),
                ()-> assertTrue(accountBasic.getDateBlockApplied().isEqual(accountBasic1.getDateBlockApplied())),

                ()-> assertTrue(accountBasic.getDatePreviousBLockType().isEqual(accountBasic1.getDatePreviousBLockType())),
                ()-> assertNull(accountBasic1.getDateTransfer()),

                ()-> assertEquals(accountBasic.getBillingCurrencyCode(),accountBasic1.getBillingCurrencyCode()),
                ()-> assertEquals(accountBasic.getPreviousAccountNumber(),accountBasic1.getPreviousAccountNumber()),
                ()-> assertEquals(accountBasic.getCustomerNumber(),accountBasic1.getCustomerNumber()),
                ()-> assertNull(accountBasic1.getCorporateNumber())


        );
    }

    private AccountBasic createAccountBasic(boolean allFields, List<Integer> fields){


        AccountBasic.AccountBasicBuilder builder = AccountBasic.builder()
                .org(001)
                .product(201)
                .accountId(UUID.randomUUID().toString().replace("-", ""))
                .blockType(BlockType.BLOCK_DECLINE)
                .billingCurrencyCode("840")
                .accountType(AccountType.CREDIT)
                .customerNumber(UUID.randomUUID().toString().replace("-", ""));


        if(allFields){
            return builder
                    .dateBlockApplied(LocalDateTime.now())
                    .datePreviousBLockType(LocalDateTime.of(2020, 12, 23, 11, 24, 30))
                    .previousBlockType(BlockType.BLOCK_SUSPECTED_FRAUD)
                    .dateTransfer(LocalDateTime.now())
                    .corporateNumber(UUID.randomUUID().toString().replace("-", ""))
                    .previousAccountNumber(UUID.randomUUID().toString().replace("-", ""))
                    .build()
                    ;

        }
        fields.forEach(integer -> populateFields(builder,integer));

        return builder.build();

    }

    private void populateFields(AccountBasic.AccountBasicBuilder builder, Integer integer) {

        switch (integer){
            case 0: {
                builder.dateBlockApplied(LocalDateTime.now());
                break;
            }
            case 1: {
                builder
                        .previousBlockType(BlockType.BLOCK_SUSPECTED_FRAUD);
                break;
            }
            case 2: {
                builder
                        .datePreviousBLockType(LocalDateTime.of(2020, 12, 23, 11, 24, 30));
                break;
            }
            case 3: {
                builder
                        .dateTransfer(LocalDateTime.now());
                break;
            }
            case 4: {
                builder
                        .previousAccountNumber(UUID.randomUUID().toString().replace("-", ""));
                break;
            }
            case 5: {
                builder
                        .corporateNumber(UUID.randomUUID().toString().replace("-", ""));
                break;
            }
        }
    }
}