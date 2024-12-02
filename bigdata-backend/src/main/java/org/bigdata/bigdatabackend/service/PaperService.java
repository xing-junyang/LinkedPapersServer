package org.bigdata.bigdatabackend.service;

import org.bigdata.bigdatabackend.vo.PaperFilterVO;
import org.bigdata.bigdatabackend.vo.PaperVO;

import java.util.List;

public interface PaperService {
    PaperVO getPaperInfoVip(Integer paperId);
    PaperVO getPaperInfoNormal(Integer paperId);
    List<PaperVO> getPapersByFilter(PaperFilterVO filterVO);
}
