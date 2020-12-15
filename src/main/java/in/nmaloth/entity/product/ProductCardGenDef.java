package in.nmaloth.entity.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("productCardGen")
public class ProductCardGenDef {

    @Id
    private ProductId productId;

    private String lastGeneratedCardNumber;
    private Integer numberIncrementBy;
    private String endingGeneratedCardNumber;
    private String startingCardNumber;
}
