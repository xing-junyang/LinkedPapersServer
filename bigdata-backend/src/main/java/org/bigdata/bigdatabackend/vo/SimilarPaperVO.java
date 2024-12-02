package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.po.SimilarPaper;

@Getter
@Setter
@NoArgsConstructor
public class SimilarPaperVO {

    private Integer similarPaperId;
    private String title;
    private double similarity;
    private Integer paperId;

    public SimilarPaper toPO() {
        SimilarPaper similarPaper = new SimilarPaper();
        similarPaper.setSimilarPaperId(this.similarPaperId);
        similarPaper.setTitle(this.title);
        similarPaper.setSimilarity(this.similarity);
        similarPaper.setPaperId(this.paperId);
        return similarPaper;
    }
}