package org.bigdata.bigdatabackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bigdata.bigdatabackend.enums.SortByEnum;

@Getter
@Setter
@NoArgsConstructor
public class PaperFilterVO {
    String keyword;
    SortByEnum sortBy;
    String category;
    int year_floor=0;
    int year_ceil=0;
}
