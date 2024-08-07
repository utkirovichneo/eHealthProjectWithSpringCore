package uz.pdp.domains.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserEmployee {
    private int authuseremployeeid;
    private int authuserid;
    private List<AuthUserAcademicDegree> degrees;
}