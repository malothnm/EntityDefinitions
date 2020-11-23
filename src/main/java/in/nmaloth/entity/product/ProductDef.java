package in.nmaloth.entity.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("productDef")

public class ProductDef {

   @Id
    private ProductId productId;

    private Integer cardsValidityMonthNew;
    private Integer cardsValidityMonthReplace;
    private Integer cardsValidityMonthReIssue;
    private Integer dateRangeNewExpDate;
    private Integer cardsWaiverActivationDays;
    private Integer daysToCardsValid;
    private Boolean cardsActivationRequired;




}
