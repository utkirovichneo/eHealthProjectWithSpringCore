package uz.pdp.domains.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserAddress {
    private int authuseraddressid;
    private int authuserpassportid;
    private String country;
    private String city;
    private String district;
    private String street;
    private String apartment;
}