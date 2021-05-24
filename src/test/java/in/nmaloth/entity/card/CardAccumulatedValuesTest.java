package in.nmaloth.entity.card;

import in.nmaloth.entity.account.AccountAccumValues;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CardAccumulatedValuesTest {

    @Test
    void serializeData() throws IOException, ClassNotFoundException {

        CardAccumulatedValues cardAccumulatedValues = createCardAccumValues(UUID.randomUUID().toString().replace("-",""),true,null);

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialCardAccum.txt"));

        cardAccumulatedValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialCardAccum.txt"));


        CardAccumulatedValues cardAccumulatedValues1 = new CardAccumulatedValues();
        cardAccumulatedValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(cardAccumulatedValues.getCardId(),cardAccumulatedValues1.getCardId()),
                ()-> assertEquals(cardAccumulatedValues.getOrg(),cardAccumulatedValues1.getOrg()),
                ()-> assertEquals(cardAccumulatedValues.getProduct(),cardAccumulatedValues1.getProduct()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).size(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).size(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).size(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).size(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).size(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).size(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber())


        );

    }

    @Test
    void serializeData1() throws IOException, ClassNotFoundException {

        CardAccumulatedValues cardAccumulatedValues = createCardAccumValues(UUID.randomUUID().toString().replace("-",""),false, Arrays.asList(0));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialCardAccum.txt"));

        cardAccumulatedValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialCardAccum.txt"));


        CardAccumulatedValues cardAccumulatedValues1 = new CardAccumulatedValues();
        cardAccumulatedValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(cardAccumulatedValues.getCardId(),cardAccumulatedValues1.getCardId()),
                ()-> assertEquals(cardAccumulatedValues.getOrg(),cardAccumulatedValues1.getOrg()),
                ()-> assertEquals(cardAccumulatedValues.getProduct(),cardAccumulatedValues1.getProduct()),
                ()-> assertNull(cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).size(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).size(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).size(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber())



        );

    }

    @Test
    void serializeData2() throws IOException, ClassNotFoundException {

        CardAccumulatedValues cardAccumulatedValues = createCardAccumValues(UUID.randomUUID().toString().replace("-",""),false,Arrays.asList(1));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialCardAccum.txt"));

        cardAccumulatedValues.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialCardAccum.txt"));


        CardAccumulatedValues cardAccumulatedValues1 = new CardAccumulatedValues();
        cardAccumulatedValues1.fromData(dataInput);


        assertAll(
                ()-> assertEquals(cardAccumulatedValues.getCardId(),cardAccumulatedValues1.getCardId()),
                ()-> assertEquals(cardAccumulatedValues.getOrg(),cardAccumulatedValues1.getOrg()),
                ()-> assertEquals(cardAccumulatedValues.getProduct(),cardAccumulatedValues1.getProduct()),
                ()-> assertNull(cardAccumulatedValues1.getPeriodicTypePeriodicCardLimitMap()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).size(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).size(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).size(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).size()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.SINGLE).get(LimitType.NO_SPECIFIC).getTransactionAmount()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.MONTHLY).get(LimitType.CASH).getTransactionNumber()),
                ()-> assertEquals(cardAccumulatedValues.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber(),
                        cardAccumulatedValues1.getPeriodicCardAccumulatedValueMap().get(PeriodicType.DAILY).get(LimitType.RETAIL).getTransactionNumber())


        );

    }




    private CardAccumulatedValues createCardAccumValues(String cardNumber, boolean allFields, List<Integer> fields) {



        Map<PeriodicType, Map<LimitType, PeriodicCardAmount>> periodicMap = new HashMap<>();

        Map<PeriodicType, Map<LimitType, PeriodicCardAmount>> periodicLimitMap = new HashMap<>();


        Map<LimitType, PeriodicCardAmount> limitAmountMapSingle = new HashMap<>();
        Map<LimitType, PeriodicCardAmount> limitAmountMapDaily = new HashMap<>();
        Map<LimitType, PeriodicCardAmount> limitAmountMapMonthly = new HashMap<>();

        Map<LimitType, PeriodicCardAmount> limitMapSingle = new HashMap<>();
        Map<LimitType, PeriodicCardAmount> limitMapDaily = new HashMap<>();
        Map<LimitType, PeriodicCardAmount> limitMapMonthly = new HashMap<>();


        createLimitTypeMap(limitAmountMapSingle);
        createLimitTypeMap(limitAmountMapDaily);
        createLimitTypeMap(limitAmountMapMonthly);


        createLimitTypeMap(limitMapSingle);
        createLimitTypeMap(limitMapDaily);
        createLimitTypeMap(limitMapMonthly);



        periodicMap.put(PeriodicType.SINGLE, limitAmountMapSingle);
        periodicMap.put(PeriodicType.DAILY, limitAmountMapDaily);
        periodicMap.put(PeriodicType.MONTHLY, limitAmountMapMonthly);


        periodicLimitMap.put(PeriodicType.SINGLE, limitMapSingle);
        periodicLimitMap.put(PeriodicType.DAILY, limitMapDaily);
        periodicLimitMap.put(PeriodicType.MONTHLY, limitMapMonthly);


        CardAccumulatedValues.CardAccumulatedValuesBuilder builder = CardAccumulatedValues.builder()
                .cardId(cardNumber)
                .org(001)
                .product(201)
                .lastUpdatedDateTime(LocalDateTime.now())
                ;

        if(allFields){
            return builder.periodicCardAccumulatedValueMap(periodicMap)
                    .periodicTypePeriodicCardLimitMap(periodicLimitMap)
                    .build()
            ;
        }

        if(fields.contains(1)){
            return builder.periodicCardAccumulatedValueMap(periodicMap).build();
        }

        return builder .periodicTypePeriodicCardLimitMap(periodicLimitMap).build();


    }

    private void createLimitTypeMap( Map<LimitType, PeriodicCardAmount> limitAmountMap){

        PeriodicCardAmount periodicCardAmountNoSpecific = PeriodicCardAmount.builder()
                .transactionAmount(1000L)
                .transactionNumber(100)
                .limitType(LimitType.NO_SPECIFIC)
                .build();

        PeriodicCardAmount periodicCardAmountRetail = PeriodicCardAmount.builder()
                .transactionAmount(2000L)
                .transactionNumber(200)
                .limitType(LimitType.RETAIL)
                .build();

        PeriodicCardAmount periodicCardAmountCash = PeriodicCardAmount.builder()
                .transactionNumber(300)
                .transactionAmount(3000L)
                .limitType(LimitType.CASH)
                .build();
        PeriodicCardAmount periodicCardAmountOTC = PeriodicCardAmount.builder()
                .transactionAmount(4000L)
                .transactionNumber(400)
                .limitType(LimitType.OTC)
                .build();

        limitAmountMap.put(periodicCardAmountNoSpecific.getLimitType(), periodicCardAmountNoSpecific);
        limitAmountMap.put(periodicCardAmountRetail.getLimitType(), periodicCardAmountRetail);
        limitAmountMap.put(periodicCardAmountCash.getLimitType(), periodicCardAmountCash);
        limitAmountMap.put(periodicCardAmountOTC.getLimitType(), periodicCardAmountOTC);

    }
}