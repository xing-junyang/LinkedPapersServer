package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.enums.SortByEnum;

@Getter
@Setter
@NoArgsConstructor
public class PaperFilterVO {
    String keywords;
    SortByEnum sortBy;
    String category;
    int yearStart =0;
    int yearEnd =0;
}
