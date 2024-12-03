package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecommendationVO {

    private Integer paperId;
    private String title;
    private String abstractText;
    private String reason;
    private Double similarity;

    public RecommendationVO(Integer paperId, String title, String abstractText, String reason, Double similarity) {
        this.paperId = paperId;
        this.title = title;
        this.abstractText = abstractText;
        this.reason = reason;
        this.similarity = similarity;
    }
}