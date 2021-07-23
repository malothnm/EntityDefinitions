package in.nmaloth.entity.product;

import in.nmaloth.entity.RegionNames;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.gemfire.ScopeType;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ReplicateRegion(name = RegionNames.CRYPTO_PARAM,scope = ScopeType.DISTRIBUTED_ACK)
public class ProductCrypto  {

    private ProductId productId;
    private boolean supportFallback;
    private List<CVM> supportedContactCVM;

}
