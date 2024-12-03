//package org.bigdata.bigdatabackend.vo;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.bigdata.bigdatabackend.po.SimilarPaper;
//
//@Getter
//@Setter
//@NoArgsConstructor
//public class SimilarPaperVO {
//
//    private Integer similarPaperId;
//    private String title;
//    private double similarity;
//    private Integer paperId;
//
//    public SimilarPaper toPO() {
//        SimilarPaper similarPaper = new SimilarPaper();
//        similarPaper.setSimilarPaperId(this.similarPaperId);
//        similarPaper.setTitle(this.title);
//        similarPaper.setSimilarity(this.similarity);
//        similarPaper.setPaperId(this.paperId);
//        return similarPaper;
//    }
//}

package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
public class SimilarPaperVO {

    private Integer paperId;
    private List<Integer> similarPapers = new ArrayList<>();
    private List<Double> similarities = new ArrayList<>();

    public void addSimilarPaperAndSimilarity(Integer similarPaper, Double similarity) {
        if (similarPaper != null && similarity != null) {
            similarPapers.add(similarPaper);
            similarities.add(similarity);
        }
    }
}