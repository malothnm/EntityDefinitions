package in.nmaloth.entity.instrument;

import in.nmaloth.entity.BlockType;
import in.nmaloth.entity.account.AccountDef;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Region("instrument")
public class Instrument  {

    @Id
    private String instrumentNumber;

    private InstrumentType instrumentType;


    private boolean active;

    @Indexed
    private String cardNumber;
    private Set<AccountDef> accountDefSet;
    private String customerNumber;

    private String corporateNumber;

    private BlockType blockType;
    private LocalDate expiryDate;
    private int org;
    private int product;


}
