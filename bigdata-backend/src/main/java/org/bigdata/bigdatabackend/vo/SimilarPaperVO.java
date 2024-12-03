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
   private List<SimilarPaperResultVO> similarPapers=new ArrayList<>();

    public void addSimilarPapers(Integer similarPaper, String title,Double similarity) {
        if (similarPaper != null && similarity != null&&title!=null) {
            SimilarPaperResultVO similarPaperResultVO = new SimilarPaperResultVO();
            similarPaperResultVO.setPaperId(similarPaper);
            similarPaperResultVO.setTitle(title);
            similarPaperResultVO.setSimilarity(similarity);
            similarPapers.add(similarPaperResultVO);
        }
    }
}