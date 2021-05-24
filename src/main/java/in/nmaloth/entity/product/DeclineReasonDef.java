package in.nmaloth.entity.product;


import in.nmaloth.entity.RegionNames;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ReplicateRegion(name = RegionNames.DECLINE_REASON,scope = ScopeType.DISTRIBUTED_ACK)
public class DeclineReasonDef {


    @Id
    private String serviceName;

    private List<DeclineReason> declineReasonList;




}
