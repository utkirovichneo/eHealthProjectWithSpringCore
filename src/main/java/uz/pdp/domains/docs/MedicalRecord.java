package uz.pdp.domains.docs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecord {
    private int medicalrecordid;
    private int medicalhistoryid;
    private int authuserdoctorid;
    private LocalDateTime dateofdiagnosis;
    private String diagnosis;
    private String treatment;
    private String notes;
}