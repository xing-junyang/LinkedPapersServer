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


    public Paper toPO() {
        Paper paper = new Paper();
        paper.setPaperId(this.paperId);
        paper.setTitle(this.title);
        paper.setAbstractText(this.abstractText);
        paper.setYear(this.year);
        paper.setCategory(this.category);
        return paper;
    }
}