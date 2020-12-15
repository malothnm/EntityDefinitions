package in.nmaloth.entity.product;

import in.nmaloth.entity.card.PeriodicCardAmount;
import in.nmaloth.entity.card.PeriodicType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("productLimit")
public class ProductLimitsDef {

    @Id
    private ProductId productId;
    private Map<PeriodicType, List<PeriodicCardAmount>> cardLimitMap;

}
