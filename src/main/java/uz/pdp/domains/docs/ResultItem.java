package uz.pdp.domains.docs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultItem {
    private int resultitemid;
    private int analysisdocumentid;
    private String name;
    private String result;
}