package in.nmaloth.entity.card;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Region("plastic")
public class Plastic {

    @Id
    private PlasticKey plasticKey;

    @Indexed
    private String cardNumber;

    private LocalDate expiryDate;
    private Boolean cardActivated;
    private LocalDateTime cardActivatedDate;
    private LocalDateTime datePlasticIssued;
    private LocalDate dateCardValidFrom;
    private Duration activationWaiveDuration;
    private String pendingCardAction;
    private String cardAction;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plastic)) return false;
        Plastic plastic = (Plastic) o;
        return getPlasticKey().equals(plastic.getPlasticKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber());
    }

}
