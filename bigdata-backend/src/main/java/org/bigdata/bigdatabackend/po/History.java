package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.vo.HistoryVO;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "history", indexes = {
        @Index(name = "idx_history_id", columnList = "history_id"),
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_paper_id", columnList = "paper_id")
})
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false, unique = true)
    private Integer historyId;

    @Basic
    @Column(name = "user_id")
    private Integer userId;

    @Basic
    @Column(name = "paper_id")
    private Integer paperId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "view_time")
    private Date viewTime;

    public HistoryVO toVO() {
        HistoryVO historyVO = new HistoryVO();
        historyVO.setHistoryId(this.historyId);
        historyVO.setUserId(this.userId);
        historyVO.setPaperId(this.paperId);
        historyVO.setTitle(this.title);
        historyVO.setViewTime(this.viewTime);
        return historyVO;
    }
}