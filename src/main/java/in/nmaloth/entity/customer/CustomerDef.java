package in.nmaloth.entity.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Region("customerDef")
public class CustomerDef {


    @Id
    private String customerNumber;

    private AddressType addressType;
    private CustomerType customerType;

    private String customerName;

    private String addressLine1;
    private String addressLine2;

    private String postalCode;
    private String state;
    private String countryCode;


    private Map<CustomerIDType,String> customerIDMap;

    // To Support Multiple Addresses during Authorization

    private String primaryPhoneNumber;
    private String primaryEmail;




}
