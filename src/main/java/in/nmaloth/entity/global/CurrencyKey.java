package in.nmaloth.entity.global;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyKey implements DataSerializable {

    private String currencyCode;
    private String destinationCurrencyCode;
    private LocalDateTime conversionDateTime;

    @Override
    public void toData(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(currencyCode);
        dataOutput.writeUTF(destinationCurrencyCode);
        dataOutput.writeUTF(conversionDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {

        currencyCode = dataInput.readUTF();
        destinationCurrencyCode = dataInput.readUTF();
        conversionDateTime = LocalDateTime.parse(dataInput.readUTF(),DateTimeFormatter.ISO_DATE_TIME);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyKey)) return false;
        CurrencyKey that = (CurrencyKey) o;
        return getCurrencyCode().equals(that.getCurrencyCode()) &&
                getDestinationCurrencyCode().equals(that.getDestinationCurrencyCode()) &&
                getConversionDateTime().equals(that.getConversionDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrencyCode(), getDestinationCurrencyCode(), getConversionDateTime());
    }

    @Override
    public String toString() {
        return "CurrencyKey{" +
                "currencyCode='" + currencyCode + '\'' +
                ", destinationCurrencyCode='" + destinationCurrencyCode + '\'' +
                ", conversionDateTime=" + conversionDateTime +
                '}';
    }
}
