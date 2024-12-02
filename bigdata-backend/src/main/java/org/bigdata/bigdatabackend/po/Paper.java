package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.vo.PaperVO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "papers", indexes = {
        @Index(name = "idx_paper_id", columnList = "paper_id"),
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_category", columnList = "category")
})
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id", nullable = false, unique = true)
    private Integer paperId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "abstract_text", length = 10000)
    private String abstractText;

    @Basic
    @Column(name = "year")
    private int year;

    @Basic
    @Column(name = "category")
    private String category;

    public PaperVO toVO() {
        PaperVO paperVO = new PaperVO();
        paperVO.setPaperId(this.paperId);
        paperVO.setTitle(this.title);
        paperVO.setAbstractText(this.abstractText);
        paperVO.setYear(this.year);
        paperVO.setCategory(this.category);
        return paperVO;
    }


}