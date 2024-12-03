package org.bigdata.bigdatabackend.service;

import org.bigdata.bigdatabackend.vo.PaperVO;
import org.bigdata.bigdatabackend.vo.RecommendationVO;

import java.util.List;

public interface RecommendationService {
    List<RecommendationVO> getRecommendations(Integer userId);
}