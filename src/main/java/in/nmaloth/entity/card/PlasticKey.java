package in.nmaloth.entity.card;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PlasticKey {

    private String id;
    private String cardNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlasticKey)) return false;
        PlasticKey that = (PlasticKey) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber());
    }
}
