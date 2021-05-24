package in.nmaloth.entity.product;

import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ReplicateRegion(name = RegionNames.PRODUCT_CARD_GEN_DEF,scope = ScopeType.DISTRIBUTED_ACK)
public class ProductCardGenDef {

    @Id
    private ProductId productId;

    private String lastGeneratedCardNumber;
    private Integer numberIncrementBy;
    private String endingGeneratedCardNumber;
    private String startingCardNumber;
}
