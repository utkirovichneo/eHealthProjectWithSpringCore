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
public class AuthUserAcademicDegree {
    private int authuseracademicdegree;
    private int authuseremployeeid;
    private String degree;
    private String graduated;
    private LocalDate yeargraduated;
}