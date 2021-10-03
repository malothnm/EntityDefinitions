package in.nmaloth.entity.product;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAuthCriteriaKey {

    private int org;
    private int product;
    private int criteria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAuthCriteriaKey)) return false;
        ProductAuthCriteriaKey that = (ProductAuthCriteriaKey) o;
        return getOrg() == that.getOrg() && getProduct() == that.getProduct() && getCriteria() == that.getCriteria();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrg(), getProduct(), getCriteria());
    }
}
