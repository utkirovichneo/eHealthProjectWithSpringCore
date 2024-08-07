package uz.pdp.domains.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserPermission {
    private int authuserpermissionid;
    private String name;
    private String code;
}