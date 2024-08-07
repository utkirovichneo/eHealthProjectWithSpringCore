package uz.pdp.domains.docs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalysisDocument {
    private int analysisdocumentid;
    private int analysisresultid;
    private LocalDateTime analysisresultdate;
    private String analysistype;
    private Map<String, String> results;
}