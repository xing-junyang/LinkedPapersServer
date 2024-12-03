package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.po.Paper;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaperVO {

    private Integer paperId;
    private String title;
    private String abstractText;
    private int year;
    private String category;

    CitationVO citations;
    SimilarPaperVO similarPapers;
    List<SameCategoryPaperVO> sameCategoryPapers;



}