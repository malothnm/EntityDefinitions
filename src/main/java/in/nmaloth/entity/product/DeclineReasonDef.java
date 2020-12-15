package in.nmaloth.entity.product;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Region("declineReason")
public class DeclineReasonDef {


    @Id
    private String serviceName;

    private List<DeclineReason> declineReasonList;




}
