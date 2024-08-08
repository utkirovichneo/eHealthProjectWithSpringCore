package uz.pdp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDTO {
    private String firstname;
    private String lastname;
    private String fathersname;
    private String phonenumber;
    private String passportseries;
    private String psasportnumber;
    private LocalDate datebirth;
    private String country;
    private String city;
    private String district;
    private String street;
    private String apartment;
}