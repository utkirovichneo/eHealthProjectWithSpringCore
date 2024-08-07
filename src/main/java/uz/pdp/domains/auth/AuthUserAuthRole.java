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
public class AuthUserAuthRole {
    private int authuserauthroleid;
    private int authuserid;
    private List<AuthUserRole> roles;
}