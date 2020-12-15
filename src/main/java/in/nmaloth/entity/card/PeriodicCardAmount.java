package in.nmaloth.entity.card;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class PeriodicCardAmount {

    private LimitType limitType;
    private Long transactionAmount;
    private Integer transactionNumber;

}
