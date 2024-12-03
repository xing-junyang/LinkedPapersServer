package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class SimilarPaperResultVO {
    Integer paperId;
    String title;
    Double similarity;
}
