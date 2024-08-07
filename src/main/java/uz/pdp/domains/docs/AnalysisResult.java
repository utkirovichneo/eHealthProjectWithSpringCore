package uz.pdp.domains.docs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalysisResult {
    private int analysisresultid;
    private int medicalhistoryid;
    private String description;
}