package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.po.History;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HistoryVO {

    private Integer historyId;
    private Integer userId;
    private Integer paperId;
    private String title;
    private Date viewTime;

    public History toPO() {
        History history = new History();
        history.setHistoryId(this.historyId);
        history.setUserId(this.userId);
        history.setPaperId(this.paperId);
        history.setTitle(this.title);
        history.setViewTime(this.viewTime);
        return history;
    }
}