package in.nmaloth.entity.instrument;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentCryptoTest {

    @Test
    void serialize() throws IOException, ClassNotFoundException {

        List<CryptoInfo> cryptoInfoList = new ArrayList<>();


        CryptoInfo cryptoInfo1 = CryptoInfo.builder()
                .expiryDate(LocalDate.now())
                .pinOffset(10)
                .serviceCode("201")
                .pinBlockFormat(PinBlockFormat.ISO_1)
                .dynamicCvv(true)
                .build();

        CryptoInfo cryptoInfo2 = CryptoInfo.builder()
                .expiryDate(LocalDate.now())
                .pinOffset(105)
                .serviceCode("101")
                .pinBlockFormat(PinBlockFormat.ISO_2)
                .dynamicCvv(false)
                .build();

        cryptoInfoList.add(cryptoInfo1);
        cryptoInfoList.add(cryptoInfo2);

        InstrumentCrypto instrumentCrypto = InstrumentCrypto.builder()
                .instrument(UUID.randomUUID().toString())
                .cryptoInfoList(cryptoInfoList)
                .chipInfoList(new ArrayList<>())
                .build();
                ;

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/instrument_crypto.txt"));
        instrumentCrypto.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/instrument_crypto.txt"));

        InstrumentCrypto instrumentCrypto1 = new InstrumentCrypto();
        instrumentCrypto1.fromData(dataInput);

        CryptoInfo cryptoInfo_deserial1 = instrumentCrypto1.getCryptoInfoList().get(0);
        CryptoInfo cryptoInfo_deserial2 = instrumentCrypto.getCryptoInfoList().get(1);

        assertAll(
                ()-> assertEquals(instrumentCrypto.getInstrument(),instrumentCrypto1.getInstrument()),
                ()-> assertEquals(instrumentCrypto.getCryptoInfoList().size(),instrumentCrypto1.getCryptoInfoList().size()),
                ()-> assertTrue(cryptoInfo1.getExpiryDate().isEqual(cryptoInfo_deserial1.getExpiryDate())),
                ()-> assertEquals(cryptoInfo1.getPinBlockFormat(),cryptoInfo_deserial1.getPinBlockFormat()),
                ()-> assertEquals(cryptoInfo1.getPinOffset(),cryptoInfo_deserial1.getPinOffset()),
                ()-> assertEquals(cryptoInfo1.getServiceCode(),cryptoInfo_deserial1.getServiceCode()),
                ()-> assertEquals(cryptoInfo1.isDynamicCvv(),cryptoInfo_deserial1.isDynamicCvv()),
                ()-> assertTrue(cryptoInfo2.getExpiryDate().isEqual(cryptoInfo_deserial2.getExpiryDate())),
                ()-> assertEquals(cryptoInfo2.getPinBlockFormat(),cryptoInfo_deserial2.getPinBlockFormat()),
                ()-> assertEquals(cryptoInfo2.getPinOffset(),cryptoInfo_deserial2.getPinOffset()),
                ()-> assertEquals(cryptoInfo2.getServiceCode(),cryptoInfo_deserial2.getServiceCode()),
                ()-> assertEquals(cryptoInfo2.isDynamicCvv(),cryptoInfo_deserial2.isDynamicCvv())
        );

    }

    @Test
    void serialize1() throws IOException, ClassNotFoundException {

        List<CryptoInfo> cryptoInfoList = new ArrayList<>();

        List<ChipInfo> chipInfoList = new ArrayList<>();

        ChipInfo chipInfo1 = ChipInfo.builder()
                .chipSeq(1)
                .chipVersion("MCHIP4")
                .atc(100)
                .build();

        ChipInfo chipInfo2 = ChipInfo.builder()
                .chipSeq(2)
                .chipVersion("MCHIP4")
                .atc(200)
                .build();
        chipInfoList.add(chipInfo1);
        chipInfoList.add(chipInfo2);


        CryptoInfo cryptoInfo1 = CryptoInfo.builder()
                .expiryDate(LocalDate.now())
                .pinOffset(10)
                .serviceCode("201")
                .pinBlockFormat(PinBlockFormat.ISO_1)
                .dynamicCvv(true)
                .build();

        CryptoInfo cryptoInfo2 = CryptoInfo.builder()
                .expiryDate(LocalDate.now())
                .pinOffset(105)
                .serviceCode("101")
                .pinBlockFormat(PinBlockFormat.ISO_2)
                .dynamicCvv(false)
                .build();

        cryptoInfoList.add(cryptoInfo1);
        cryptoInfoList.add(cryptoInfo2);

        InstrumentCrypto instrumentCrypto = InstrumentCrypto.builder()
                .instrument(UUID.randomUUID().toString())
                .cryptoInfoList(cryptoInfoList)
                .chipInfoList(chipInfoList)
                .build();
        ;

        DataOutputStream dataOutput = new DataOutputStream(new FileOutputStream("src/test/resources/instrument_crypto.txt"));
        instrumentCrypto.toData(dataOutput);

        DataInput dataInput = new DataInputStream(new FileInputStream("src/test/resources/instrument_crypto.txt"));

        InstrumentCrypto instrumentCrypto1 = new InstrumentCrypto();
        instrumentCrypto1.fromData(dataInput);

        CryptoInfo cryptoInfo_deserial1 = instrumentCrypto1.getCryptoInfoList().get(0);
        CryptoInfo cryptoInfo_deserial2 = instrumentCrypto.getCryptoInfoList().get(1);

        ChipInfo chipInfo_deserialise1 = instrumentCrypto1.getChipInfoList().get(0);
        ChipInfo chipInfo_deserialise2 = instrumentCrypto1.getChipInfoList().get(1);
        assertAll(
                ()-> assertEquals(instrumentCrypto.getInstrument(),instrumentCrypto1.getInstrument()),
                ()-> assertEquals(instrumentCrypto.getCryptoInfoList().size(),instrumentCrypto1.getCryptoInfoList().size()),
                ()-> assertTrue(cryptoInfo1.getExpiryDate().isEqual(cryptoInfo_deserial1.getExpiryDate())),
                ()-> assertEquals(cryptoInfo1.getPinBlockFormat(),cryptoInfo_deserial1.getPinBlockFormat()),
                ()-> assertEquals(cryptoInfo1.getPinOffset(),cryptoInfo_deserial1.getPinOffset()),
                ()-> assertEquals(cryptoInfo1.getServiceCode(),cryptoInfo_deserial1.getServiceCode()),
                ()-> assertEquals(cryptoInfo1.isDynamicCvv(),cryptoInfo_deserial1.isDynamicCvv()),
                ()-> assertTrue(cryptoInfo2.getExpiryDate().isEqual(cryptoInfo_deserial2.getExpiryDate())),
                ()-> assertEquals(cryptoInfo2.getPinBlockFormat(),cryptoInfo_deserial2.getPinBlockFormat()),
                ()-> assertEquals(cryptoInfo2.getPinOffset(),cryptoInfo_deserial2.getPinOffset()),
                ()-> assertEquals(cryptoInfo2.getServiceCode(),cryptoInfo_deserial2.getServiceCode()),
                ()-> assertEquals(cryptoInfo2.isDynamicCvv(),cryptoInfo_deserial2.isDynamicCvv()),
                ()-> assertEquals(chipInfo1.getChipSeq(),chipInfo_deserialise1.getChipSeq()),
                ()-> assertEquals(chipInfo1.getChipVersion(),chipInfo_deserialise1.getChipVersion()),
                ()-> assertEquals(chipInfo1.getAtc(),chipInfo_deserialise1.getAtc()),
                ()-> assertEquals(chipInfo2.getChipSeq(),chipInfo_deserialise2.getChipSeq()),
                ()-> assertEquals(chipInfo2.getChipVersion(),chipInfo_deserialise2.getChipVersion()),
                ()-> assertEquals(chipInfo2.getAtc(),chipInfo_deserialise2.getAtc())
        );

    }


    @Test
    void fromData() {
    }
}