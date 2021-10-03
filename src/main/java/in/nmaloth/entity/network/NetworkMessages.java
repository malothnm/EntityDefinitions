package in.nmaloth.entity.network;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.payments.constants.ServiceResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.expiration.TimeToLiveExpiration;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReplicateRegion(name = RegionNames.NETWORK_MESSAGES, scope = ScopeType.DISTRIBUTED_ACK)
@TimeToLiveExpiration(action = "DESTROY",timeout = "60")

public class NetworkMessages {

    @Id
    private String messageId;

    private String incomingMessageId;
    private MessageStatus messageStatus;
    private String responseCode;


}
