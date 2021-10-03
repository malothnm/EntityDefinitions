package in.nmaloth.entity.card;

import in.nmaloth.entity.RegionNames;
import in.nmaloth.payments.constants.schemeDatabase.ExceptionActionCodes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class SchemeException {

    @Id
    private String instrument;

    private LocalDateTime exceptionDateTime;
    private ExceptionActionCodes exceptionActionCodes;

}
