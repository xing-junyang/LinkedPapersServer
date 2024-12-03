//package org.bigdata.bigdatabackend.po;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.bigdata.bigdatabackend.vo.CitationVO;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "citations", indexes = {
//        @Index(name = "idx_citation_id", columnList = "citation_id"),
//        @Index(name = "idx_title", columnList = "title")
//})
//public class Citation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "citation_id", nullable = false, unique = true)
//    private Integer citationId;
//
//    @Basic
//    @Column(name = "title")
//    private String title;
//
//    @Basic
//    @Column(name = "year")
//    private int year;
//
//    @Basic
//    @JoinColumn(name = "paper_id")
//    private Integer paperId;
//
//    public CitationVO toVO() {
//        CitationVO citationVO = new CitationVO();
//        citationVO.setCitationId(this.citationId);
//        citationVO.setTitle(this.title);
//        citationVO.setYear(this.year);
//        citationVO.setPaperId(this.paperId);
//        return citationVO;
//    }
//
//}

package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.repository.PaperRepository;
import org.bigdata.bigdatabackend.vo.CitationResultVO;
import org.bigdata.bigdatabackend.vo.CitationVO;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "citations", indexes = {
        @Index(name = "idx_paper_id", columnList = "paper_id"),
})
public class Citation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id", nullable = false, unique = true)
    private Integer paperId;


    @Basic
    @Column(name = "citations", length = 10000)  // 设置长度为1000
    private String citations;  // 这是一个逗号分隔的字符串  // 这是一个逗号分隔的字符串

    @Basic
    @Column(name = "citation_count")
    private Integer citationCount;




    /**
     * 将 Citation PO 转换为 VO。
     * 将 citations 字段从逗号分隔的字符串转换为 List<Integer>。
     */
    public CitationVO toVO() {
        CitationVO citationVO = new CitationVO();
        citationVO.setPaperId(this.paperId);




        citationVO.setCitationCount(this.citationCount);

        return citationVO;
    }
}
