package in.nmaloth.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductId {

    private int org;
    private int product;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductId)) return false;
        ProductId productId = (ProductId) o;
        return getOrg() == productId.getOrg() &&
                getProduct() == productId.getProduct();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrg(), getProduct());
    }
}
