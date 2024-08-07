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
public class AuthUserRole {
    private int authuserroleid;
    private String name;
    private String code;
    private List<AuthUserPermission> permissions;
}