package in.nmaloth.function.model.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerInput {

    private String messageId;
    private String messageTypeId;
    private String address;
    private String name;
    private String zipCode;

}
