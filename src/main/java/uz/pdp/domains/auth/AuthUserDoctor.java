package uz.pdp.domains.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDoctor {
    private int authuserdoctorid;
    private int authuseremployeeid;
    private String specialistdoctor;
}