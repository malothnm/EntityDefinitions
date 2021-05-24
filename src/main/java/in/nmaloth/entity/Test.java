package in.nmaloth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Region("test")
public class Test {

    @Id
    private String id;

    private String value;
    private Integer intValue;
    private String test2;
}
