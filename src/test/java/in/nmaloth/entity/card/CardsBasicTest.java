package in.nmaloth.entity.card;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.account.AccountDef;
import in.nmaloth.entity.account.AccountType;
import in.nmaloth.payments.constants.EntryMode;
import in.nmaloth.payments.constants.PurchaseTypes;
import in.nmaloth.payments.constants.TerminalType;
import in.nmaloth.payments.constants.TransactionType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CardsBasicTest {

    @Test
    void toData() throws IOException, ClassNotFoundException {

        CardsBasic cardsBasic = createCardBasic(UUID.randomUUID().toString().replace("-",""),true,null);

        Plastic plastic = cardsBasic.getPlasticList().get(0);
        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialCardBasic.txt"));

        cardsBasic.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialCardBasic.txt"));


        CardsBasic cardsBasic1 = new CardsBasic();

        cardsBasic1.fromData(dataInput);

        Plastic plastic1 = cardsBasic1.getPlasticList().get(0);

        assertAll(

                ()-> assertEquals(cardsBasic.getCardId(),cardsBasic1.getCardId()),
                ()-> assertEquals(cardsBasic.getOrg(),cardsBasic1.getOrg()),
                ()-> assertEquals(cardsBasic.getProduct(),cardsBasic1.getProduct()),
                ()-> assertEquals(cardsBasic.getCardStatus(),cardsBasic1.getCardStatus()),
                ()-> assertEquals(cardsBasic.getCardholderType(),cardsBasic1.getCardholderType()),
                ()-> assertEquals(cardsBasic.getBlockType(),cardsBasic1.getBlockType()),
                ()-> assertEquals(cardsBasic.getWaiverDaysActivation(),cardsBasic1.getWaiverDaysActivation()),
                ()-> assertEquals(cardsBasic.getCardReturnNumber(),cardsBasic1.getCardReturnNumber()),
                    ()-> assertEquals(cardsBasic.getPlasticList().size(),cardsBasic1.getPlasticList().size()),
                ()-> assertEquals(plastic.getPlasticId(),plastic1.getPlasticId()),
                ()-> assertTrue(plastic.getExpiryDate().isEqual(plastic1.getExpiryDate())),
                ()-> assertEquals(plastic.getCardActivated(),plastic1.getCardActivated()),
                ()-> assertTrue(plastic.getCardActivatedDate().isEqual(plastic1.getCardActivatedDate())),
                ()-> assertTrue(plastic.getDatePlasticIssued().isEqual(plastic1.getDatePlasticIssued())),
                ()-> assertTrue(plastic.getDateCardValidFrom().isEqual(plastic1.getDateCardValidFrom())),
                ()-> assertEquals(plastic.getActivationWaiveDuration().toDays(),plastic1.getActivationWaiveDuration().toDays()),
                ()-> assertEquals(plastic.getDynamicCVV(),plastic1.getDynamicCVV()),
                ()-> assertEquals(plastic.getPendingCardAction(),plastic1.getPendingCardAction()),
                ()-> assertEquals(plastic.getCardAction(),plastic1.getCardAction()),
                ()-> assertEquals(cardsBasic.getBlockTransactionType().size(),cardsBasic1.getBlockTransactionType().size()),
                ()-> assertEquals(cardsBasic.getBlockTerminalType().size(),cardsBasic1.getBlockTerminalType().size()),
                ()-> assertEquals(cardsBasic.getBlockPurchaseTypes().size(),cardsBasic1.getBlockPurchaseTypes().size()),
                ()-> assertEquals(cardsBasic.getBlockCashBack(),cardsBasic1.getBlockCashBack()),
                ()-> assertEquals(cardsBasic.getBlockInstallments(),cardsBasic1.getBlockInstallments())

        );
    }


    @Test
    void toData1() throws IOException, ClassNotFoundException {

        CardsBasic cardsBasic = createCardBasic(UUID.randomUUID().toString().replace("-",""),false,Arrays.asList(0,2,4,6,8,10,12,14));

        Plastic plastic = cardsBasic.getPlasticList().get(0);
        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialCardBasic.txt"));

        cardsBasic.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialCardBasic.txt"));


        CardsBasic cardsBasic1 = new CardsBasic();

        cardsBasic1.fromData(dataInput);

        Plastic plastic1 = cardsBasic1.getPlasticList().get(0);

        assertAll(

                ()-> assertEquals(cardsBasic.getCardId(),cardsBasic1.getCardId()),
                ()-> assertEquals(cardsBasic.getOrg(),cardsBasic1.getOrg()),
                ()-> assertEquals(cardsBasic.getProduct(),cardsBasic1.getProduct()),
                ()-> assertEquals(cardsBasic.getCardStatus(),cardsBasic1.getCardStatus()),
                ()-> assertEquals(cardsBasic.getCardholderType(),cardsBasic1.getCardholderType()),
                ()-> assertEquals(cardsBasic.getBlockType(),cardsBasic1.getBlockType()),
                ()-> assertNull(cardsBasic1.getWaiverDaysActivation()),
                ()-> assertEquals(cardsBasic.getCardReturnNumber(),cardsBasic1.getCardReturnNumber()),
                ()-> assertEquals(cardsBasic.getPlasticList().size(),cardsBasic1.getPlasticList().size()),
                ()-> assertEquals(plastic.getPlasticId(),plastic1.getPlasticId()),
                ()-> assertTrue(plastic.getExpiryDate().isEqual(plastic1.getExpiryDate())),
                ()-> assertEquals(plastic.getCardActivated(),plastic1.getCardActivated()),
                ()-> assertTrue(plastic.getCardActivatedDate().isEqual(plastic1.getCardActivatedDate())),
                ()-> assertTrue(plastic.getDatePlasticIssued().isEqual(plastic1.getDatePlasticIssued())),
                ()-> assertTrue(plastic.getDateCardValidFrom().isEqual(plastic1.getDateCardValidFrom())),
                ()-> assertEquals(plastic.getActivationWaiveDuration().toDays(),plastic1.getActivationWaiveDuration().toDays()),
                ()-> assertEquals(plastic.getDynamicCVV(),plastic1.getDynamicCVV()),
                ()-> assertEquals(plastic.getPendingCardAction(),plastic1.getPendingCardAction()),
                ()-> assertEquals(plastic.getCardAction(),plastic1.getCardAction()),
                ()-> assertNull(cardsBasic1.getBlockTransactionType()),
                ()-> assertEquals(cardsBasic.getBlockTerminalType().size(),cardsBasic1.getBlockTerminalType().size()),
                ()-> assertNull(cardsBasic1.getBlockPurchaseTypes()),
                ()-> assertEquals(cardsBasic.getBlockCashBack(),cardsBasic1.getBlockCashBack()),
                ()-> assertNull(cardsBasic1.getBlockInstallments()),
                ()-> assertEquals(cardsBasic1.getBlockEntryMode().size(),cardsBasic.getBlockEntryMode().size())

        );
    }


    @Test
    void toData2() throws IOException, ClassNotFoundException {

        CardsBasic cardsBasic = createCardBasic(UUID.randomUUID().toString().replace("-",""),false,Arrays.asList(1,3,5,7,9,11,13));

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/serialCardBasic.txt"));

        cardsBasic.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/serialCardBasic.txt"));


        CardsBasic cardsBasic1 = new CardsBasic();

        cardsBasic1.fromData(dataInput);

        assertAll(

                ()-> assertEquals(cardsBasic.getCardId(),cardsBasic1.getCardId()),
                ()-> assertEquals(cardsBasic.getOrg(),cardsBasic1.getOrg()),
                ()-> assertEquals(cardsBasic.getProduct(),cardsBasic1.getProduct()),
                ()-> assertEquals(cardsBasic.getCardStatus(),cardsBasic1.getCardStatus()),
                ()-> assertEquals(cardsBasic.getCardholderType(),cardsBasic1.getCardholderType()),
                ()-> assertEquals(cardsBasic.getBlockType(),cardsBasic1.getBlockType()),
                ()-> assertEquals(cardsBasic.getWaiverDaysActivation(),cardsBasic1.getWaiverDaysActivation()),
                ()-> assertNull(cardsBasic1.getCardReturnNumber()),
                ()-> assertNull(cardsBasic1.getPlasticList()),
                ()-> assertEquals(cardsBasic.getBlockTransactionType().size(),cardsBasic1.getBlockTransactionType().size()),
                ()-> assertNull(cardsBasic1.getBlockTerminalType()),
                ()-> assertEquals(cardsBasic.getBlockPurchaseTypes().size(),cardsBasic1.getBlockPurchaseTypes().size()),
                ()-> assertNull(cardsBasic1.getBlockCashBack()),
                ()-> assertEquals(cardsBasic.getBlockInstallments(),cardsBasic1.getBlockInstallments()),
                ()-> assertNull(cardsBasic1.getBlockEntryMode())

        );
    }


    private CardsBasic createCardBasic(String cardId, Boolean allFields, List<Integer> fields){

        List<Plastic> plasticList = new ArrayList<>();

        plasticList.add(
                Plastic.builder()
                        .plasticId(UUID.randomUUID().toString().replace("-",""))
                        .cardActivatedDate(LocalDateTime.now())
                        .expiryDate(LocalDate.now())
                        .activationWaiveDuration(Duration.ofDays(10))
                        .cardAction(CardAction.NEW_CARD)
                        .cardActivated(true)
                        .dynamicCVV(false)
                        .dateCardValidFrom(LocalDate.now())
                        .pendingCardAction(CardAction.NO_ACTION)
                        .datePlasticIssued(LocalDateTime.now())
                        .build()

        );
        plasticList.add(
                Plastic.builder()
                        .plasticId(UUID.randomUUID().toString().replace("-",""))
                        .expiryDate(LocalDate.now())
                        .activationWaiveDuration(Duration.ofDays(10))
                        .cardAction(CardAction.NO_ACTION)
                        .cardActivated(true)
                        .dynamicCVV(false)
                        .dateCardValidFrom(LocalDate.now())
                        .pendingCardAction(CardAction.NEW_CARD)
                        .build()

        );


        Set<AccountDef> accountDefSet = new HashSet<>();

        accountDefSet.add(AccountDef.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-",""))
                .billingCurrencyCode("484")
                .accountType(AccountType.CREDIT)
                .build());

        accountDefSet.add(AccountDef.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-",""))
                .billingCurrencyCode("840")
                .accountType(AccountType.CREDIT)
                .build());

        Map<TransactionType,Boolean> transactionTypeBooleanMap = new HashMap<>();
        transactionTypeBooleanMap.put(TransactionType.PURCHASE,true);
        transactionTypeBooleanMap.put(TransactionType.ACCOUNT_FUND_TRANSACTION,true);

        Map<TerminalType,Boolean> terminalTypeBooleanMap = new HashMap<>();
        terminalTypeBooleanMap.put(TerminalType.ATM,true);
        terminalTypeBooleanMap.put(TerminalType.ELECTRONIC_CASH_REGISTER,true);

        Map<PurchaseTypes,Boolean> purchaseTypesBooleanMap = new HashMap<>();
        purchaseTypesBooleanMap.put(PurchaseTypes.AIRLINE,true);
        purchaseTypesBooleanMap.put(PurchaseTypes.GAMBLING,true);


        Map<EntryMode,Boolean> entryModeBooleanMap = new HashMap<>();
        entryModeBooleanMap.put(EntryMode.ECOMM,true);
        entryModeBooleanMap.put(EntryMode.COF,true);



        CardsBasic.CardsBasicBuilder builder = CardsBasic.builder()
                .cardId(cardId)
                .cardholderType(CardHolderType.PRIMARY)
                .blockType(BlockType.APPROVE)
                .cardStatus(CardStatus.ACTIVE)
                .org(001)
                .product(201)
                ;

        if(allFields){
            return builder
                    .waiverDaysActivation(10)
                    .cardReturnNumber(21)
                    .plasticList(plasticList)
                    .blockTransactionType(transactionTypeBooleanMap)
                    .blockTerminalType(terminalTypeBooleanMap)
                    .blockPurchaseTypes(purchaseTypesBooleanMap)
                    .blockCashBack(true)
                    .blockInstallments(true)
                    .blockEntryMode(entryModeBooleanMap)
                    .build();

        }

        fields.forEach(integer -> addToBuilder(builder,plasticList,integer));

        return builder.build();

    }

    private void addToBuilder(CardsBasic.CardsBasicBuilder builder, List<Plastic> plasticList, Integer integer) {

        Map<TransactionType,Boolean> transactionTypeBooleanMap = new HashMap<>();
        transactionTypeBooleanMap.put(TransactionType.PURCHASE,true);
        transactionTypeBooleanMap.put(TransactionType.ACCOUNT_FUND_TRANSACTION,true);

        Map<TerminalType,Boolean> terminalTypeBooleanMap = new HashMap<>();
        terminalTypeBooleanMap.put(TerminalType.ATM,true);
        terminalTypeBooleanMap.put(TerminalType.ELECTRONIC_CASH_REGISTER,true);

        Map<PurchaseTypes,Boolean> purchaseTypesBooleanMap = new HashMap<>();
        purchaseTypesBooleanMap.put(PurchaseTypes.AIRLINE,true);
        purchaseTypesBooleanMap.put(PurchaseTypes.GAMBLING,true);

        Map<EntryMode,Boolean> entryModeBooleanMap = new HashMap<>();
        entryModeBooleanMap.put(EntryMode.ECOMM,true);
        entryModeBooleanMap.put(EntryMode.COF,true);


        switch (integer){
            case 0:
            case 2:
            case 1:
            case 5 :
            case 6:
            case 7: {
                break;
            }
            case 3: {
                builder.waiverDaysActivation(12);
                break;
            }
            case 4: {
                builder.cardReturnNumber(30);
                break;
            }
            case 8: {
                builder.plasticList(plasticList);
                break;
            }
            case 9: {
                builder.blockTransactionType(transactionTypeBooleanMap);
                break;
            }
            case 10: {
                builder.blockTerminalType(terminalTypeBooleanMap);
                break;
            }
            case 11: {
                builder
                        .blockPurchaseTypes(purchaseTypesBooleanMap);
                break;
            }
            case 12: {
                builder
                        .blockCashBack(true);
                break;
            }
            case 13: {
                builder
                        .blockInstallments(true);
                break;

            }
            case 14: {
                builder.blockEntryMode(entryModeBooleanMap);
                break;
            }
        }
    }

}