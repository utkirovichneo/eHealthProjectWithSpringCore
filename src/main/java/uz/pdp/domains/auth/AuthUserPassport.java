package uz.pdp.domains.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserPassport {
    private int authuserpassportid;
    private int authuserid;
    private String passportseries;
    private String psasportnumber;
    private String firstname;
    private String lastname;
    private String fathersname;
    private LocalDate datebirth;
}