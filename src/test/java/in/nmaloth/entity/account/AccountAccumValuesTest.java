package in.nmaloth.entity.account;

import in.nmaloth.entity.BlockType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountAccumValuesTest {

    @Test
    void serialization() throws IOException, ClassNotFoundException {

        AccountAccumValues accountAccumValues = createAccountAccumValues(true,null);

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAccum.txt"));

        accountAccumValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAccum.txt"));


        AccountAccumValues accountAccumValues1 = new AccountAccumValues();
        accountAccumValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(accountAccumValues.getAccountId(),accountAccumValues1.getAccountId()),
                ()-> assertEquals(accountAccumValues1.getOrg(),accountAccumValues1.getOrg()),
                ()-> assertEquals(accountAccumValues.getProduct(),accountAccumValues1.getProduct()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().size(),accountAccumValues1.getBalancesMap().size()),
                ()-> assertEquals(accountAccumValues.getLimitsMap().size(),accountAccumValues1.getLimitsMap().size()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().get(BalanceTypes.INSTALLMENT_BALANCE).getPostedBalance(),
                        accountAccumValues1.getBalancesMap().get(BalanceTypes.INSTALLMENT_BALANCE).getPostedBalance()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().get(BalanceTypes.CURRENT_BALANCE).getMemoCr(),
                        accountAccumValues1.getBalancesMap().get(BalanceTypes.CURRENT_BALANCE).getMemoCr()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().get(BalanceTypes.CASH_BALANCE).getMemoDb(),
                        accountAccumValues1.getBalancesMap().get(BalanceTypes.CASH_BALANCE).getMemoDb()),
                ()-> assertEquals(accountAccumValues.getAccountType(),accountAccumValues1.getAccountType())
        );
    }

    @Test
    void serialization1() throws IOException, ClassNotFoundException {

        AccountAccumValues accountAccumValues = createAccountAccumValues(false, Arrays.asList(0));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAccum.txt"));

        accountAccumValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAccum.txt"));


        AccountAccumValues accountAccumValues1 = new AccountAccumValues();
        accountAccumValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(accountAccumValues.getAccountId(),accountAccumValues1.getAccountId()),
                ()-> assertEquals(accountAccumValues1.getOrg(),accountAccumValues1.getOrg()),
                ()-> assertEquals(accountAccumValues.getProduct(),accountAccumValues1.getProduct()),
                ()-> assertNull(accountAccumValues1.getBalancesMap()),
                ()-> assertEquals(accountAccumValues.getLimitsMap().size(),accountAccumValues1.getLimitsMap().size())

        );
    }

    @Test
    void serialization2() throws IOException, ClassNotFoundException {

        AccountAccumValues accountAccumValues = createAccountAccumValues(false,Arrays.asList(1));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAccum.txt"));

        accountAccumValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAccum.txt"));


        AccountAccumValues accountAccumValues1 = new AccountAccumValues();
        accountAccumValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(accountAccumValues.getAccountId(),accountAccumValues1.getAccountId()),
                ()-> assertEquals(accountAccumValues1.getOrg(),accountAccumValues1.getOrg()),
                ()-> assertEquals(accountAccumValues.getProduct(),accountAccumValues1.getProduct()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().size(),accountAccumValues1.getBalancesMap().size()),
                ()-> assertNull(accountAccumValues1.getLimitsMap()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().get(BalanceTypes.INSTALLMENT_BALANCE).getPostedBalance(),
                        accountAccumValues1.getBalancesMap().get(BalanceTypes.INSTALLMENT_BALANCE).getPostedBalance()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().get(BalanceTypes.CURRENT_BALANCE).getMemoCr(),
                        accountAccumValues1.getBalancesMap().get(BalanceTypes.CURRENT_BALANCE).getMemoCr()),
                ()-> assertEquals(accountAccumValues.getBalancesMap().get(BalanceTypes.CASH_BALANCE).getMemoDb(),
                        accountAccumValues1.getBalancesMap().get(BalanceTypes.CASH_BALANCE).getMemoDb())
        );
    }

    @Test
    void serialization3() throws IOException, ClassNotFoundException {

        AccountAccumValues accountAccumValues = createAccountAccumValues(false,new ArrayList<>());

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialAccum.txt"));

        accountAccumValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialAccum.txt"));


        AccountAccumValues accountAccumValues1 = new AccountAccumValues();
        accountAccumValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(accountAccumValues.getAccountId(),accountAccumValues1.getAccountId()),
                ()-> assertEquals(accountAccumValues1.getOrg(),accountAccumValues1.getOrg()),
                ()-> assertEquals(accountAccumValues.getProduct(),accountAccumValues1.getProduct()),
                ()-> assertNull(accountAccumValues1.getBalancesMap()),
                ()-> assertNull(accountAccumValues1.getLimitsMap())
        );
    }


    private AccountAccumValues createAccountAccumValues(boolean allFields, List<Integer> fields){

        Map<BalanceTypes,Long> balanceLimitMap = new HashMap<>();

        balanceLimitMap.put(BalanceTypes.CURRENT_BALANCE,100000L);
        balanceLimitMap.put(BalanceTypes.CASH_BALANCE,50000L);
        balanceLimitMap.put(BalanceTypes.INSTALLMENT_BALANCE,80000L);


        Map<BalanceTypes, AccountBalances> accountBalancesMap = new HashMap<>();

        AccountBalances accountBalances1 = AccountBalances.builder()
                .postedBalance(1000)
                .memoDb(100)
                .memoCr(10)
                .build()
                ;

        AccountBalances accountBalances2 = AccountBalances.builder()
                .postedBalance(2000)
                .memoDb(200)
                .memoCr(20)
                .build()
                ;
        AccountBalances accountBalances3 = AccountBalances.builder()
                .postedBalance(3000)
                .memoDb(300)
                .memoCr(30)
                .build()
                ;
        AccountBalances accountBalances4 = AccountBalances.builder()
                .postedBalance(4000)
                .memoDb(400)
                .memoCr(40)
                .build()
                ;

        accountBalancesMap.put(BalanceTypes.CURRENT_BALANCE,accountBalances4);
        accountBalancesMap.put(BalanceTypes.CASH_BALANCE,accountBalances3);
        accountBalancesMap.put(BalanceTypes.INSTALLMENT_BALANCE,accountBalances1);


        AccountAccumValues.AccountAccumValuesBuilder builder = AccountAccumValues.builder()
                .accountId(UUID.randomUUID().toString().replace("-", ""))
                .org(1)
                .product(201)
                .accountType(AccountType.CREDIT)
                .blockType(BlockType.BLOCK_SUSPECTED_FRAUD)
                ;

        if(allFields){

            return builder.limitsMap(balanceLimitMap)
                    .balancesMap(accountBalancesMap)
                    .build();
        }

        fields.forEach(integer ->  populateRestofFields(integer,builder,balanceLimitMap,accountBalancesMap));

        return builder.build();



    }

    private void populateRestofFields(Integer integer, AccountAccumValues.AccountAccumValuesBuilder builder,
                                      Map<BalanceTypes, Long> balanceLimitMap,
                                      Map<BalanceTypes, AccountBalances> accountBalancesMap) {

        switch (integer){
            case 0: {
                builder.limitsMap(balanceLimitMap);
                return;
            }
            case 1: {
                builder.balancesMap(accountBalancesMap);
                return;
            }
        }
    }
}