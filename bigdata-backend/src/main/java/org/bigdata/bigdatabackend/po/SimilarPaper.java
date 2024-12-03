//package org.bigdata.bigdatabackend.po;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.bigdata.bigdatabackend.vo.SimilarPaperVO;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "similar_papers", indexes = {
//        @Index(name = "idx_similar_paper_id", columnList = "similar_paper_id"),
//        @Index(name = "idx_title", columnList = "title")
//})
//public class SimilarPaper {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "similar_paper_id", nullable = false, unique = true)
//    private Integer similarPaperId;
//
//    @Basic
//    @Column(name = "title")
//    private String title;
//
//    @Basic
//    @Column(name = "similarity")
//    private double similarity;
//
//    @Basic
//    @JoinColumn(name = "paper_id")
//    private Integer paperId;
//
//    public SimilarPaperVO toVO() {
//        SimilarPaperVO similarPaperVO = new SimilarPaperVO();
//        similarPaperVO.setSimilarPaperId(this.similarPaperId);
//        similarPaperVO.setTitle(this.title);
//        similarPaperVO.setSimilarity(this.similarity);
//        similarPaperVO.setPaperId(this.paperId);
//        return similarPaperVO;
//    }
//}

package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.vo.SimilarPaperVO;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "similar_papers", indexes = {
        @Index(name = "idx_paper_id", columnList = "paper_id")
})
public class SimilarPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id", nullable = false, unique = true)
    private Integer paperId;

    @Basic
    @Column(name = "similar_paper1")
    private Integer similarPaper1;

    @Basic
    @Column(name = "similarity1")
    private Double similarity1;

    @Basic
    @Column(name = "similar_paper2")
    private Integer similarPaper2;

    @Basic
    @Column(name = "similarity2")
    private Double similarity2;

    @Basic
    @Column(name = "similar_paper3")
    private Integer similarPaper3;

    @Basic
    @Column(name = "similarity3")
    private Double similarity3;

    @Basic
    @Column(name = "similar_paper4")
    private Integer similarPaper4;

    @Basic
    @Column(name = "similarity4")
    private Double similarity4;

    @Basic
    @Column(name = "similar_paper5")
    private Integer similarPaper5;

    @Basic
    @Column(name = "similarity5")
    private Double similarity5;

    @Basic
    @Column(name = "similar_paper6")
    private Integer similarPaper6;

    @Basic
    @Column(name = "similarity6")
    private Double similarity6;

    @Basic
    @Column(name = "similar_paper7")
    private Integer similarPaper7;

    @Basic
    @Column(name = "similarity7")
    private Double similarity7;

    @Basic
    @Column(name = "similar_paper8")
    private Integer similarPaper8;

    @Basic
    @Column(name = "similarity8")
    private Double similarity8;

    @Basic
    @Column(name = "similar_paper9")
    private Integer similarPaper9;

    @Basic
    @Column(name = "similarity9")
    private Double similarity9;

    @Basic
    @Column(name = "similar_paper10")
    private Integer similarPaper10;

    @Basic
    @Column(name = "similarity10")
    private Double similarity10;

    // 将PO对象转换为VO对象
    public SimilarPaperVO toVO() {
        SimilarPaperVO similarPaperVO = new SimilarPaperVO();
        similarPaperVO.setPaperId(this.paperId);

        // 使用硬编码字段填充VO中的列表
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper1, this.similarity1);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper2, this.similarity2);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper3, this.similarity3);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper4, this.similarity4);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper5, this.similarity5);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper6, this.similarity6);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper7, this.similarity7);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper8, this.similarity8);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper9, this.similarity9);
        similarPaperVO.addSimilarPaperAndSimilarity(this.similarPaper10, this.similarity10);

        return similarPaperVO;
    }
}