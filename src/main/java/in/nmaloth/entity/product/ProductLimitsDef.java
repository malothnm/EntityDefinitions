package in.nmaloth.entity.product;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.entity.card.LimitType;
import in.nmaloth.entity.card.PeriodicCardAmount;
import in.nmaloth.entity.card.PeriodicType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ReplicateRegion( name = RegionNames.PRODUCT_LIMITS,scope = ScopeType.DISTRIBUTED_ACK)
public class ProductLimitsDef {

    @Id
    private ProductId productId;
    private Map<PeriodicType, Map<LimitType,PeriodicCardAmount>> cardLimitMap;

}
