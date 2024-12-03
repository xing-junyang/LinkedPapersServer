package org.bigdata.bigdatabackend.controller;

import org.bigdata.bigdatabackend.service.RecommendationService;
import org.bigdata.bigdatabackend.vo.PaperVO;
import org.bigdata.bigdatabackend.vo.RecommendationVO;
import org.bigdata.bigdatabackend.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public ResultVO<List<RecommendationVO>> getRecommendations(@RequestParam Integer userId) {
        return ResultVO.buildSuccess(recommendationService.getRecommendations(userId));
    }
}