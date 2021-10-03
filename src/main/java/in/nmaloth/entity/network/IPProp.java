package in.nmaloth.entity.network;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IPProp {

    private String ipHost;
    private int port;

}
