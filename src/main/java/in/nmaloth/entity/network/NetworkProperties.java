package in.nmaloth.entity.network;


import in.nmaloth.entity.RegionNames;
import in.nmaloth.payments.constants.network.NetworkType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@ReplicateRegion(name = RegionNames.NETWORK_PROP, scope = ScopeType.DISTRIBUTED_ACK)

public class NetworkProperties {

    @Id
    private NetworkType networkType;

    private List<IPProp> ipProps;
    private String stationId;
    private String ica;
    private SignOnStatus signOnStatus;
    private String fwdInstId;
    private Integer networkId;

}
