package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.po.Citation;

@Getter
@Setter
@NoArgsConstructor
public class CitationVO {

    private Integer citationId;
    private String title;
    private int year;
    private Integer paperId;

    public Citation toPO() {
        Citation citation = new Citation();
        citation.setCitationId(this.citationId);
        citation.setTitle(this.title);
        citation.setYear(this.year);
        citation.setPaperId(this.paperId);
        return citation;
    }
}