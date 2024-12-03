package org.bigdata.bigdatabackend.serviceImpl;

import org.bigdata.bigdatabackend.po.History;
import org.bigdata.bigdatabackend.po.Paper;
import org.bigdata.bigdatabackend.po.SimilarPaper;
import org.bigdata.bigdatabackend.repository.HistoryRepository;
import org.bigdata.bigdatabackend.repository.PaperRepository;
import org.bigdata.bigdatabackend.repository.SimilarPaperRepository;
import org.bigdata.bigdatabackend.service.RecommendationService;
import org.bigdata.bigdatabackend.vo.PaperVO;
import org.bigdata.bigdatabackend.vo.RecommendationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private SimilarPaperRepository similarPaperRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public List<RecommendationVO> getRecommendations(Integer userId) {
        List<History> histories = historyRepository.findByUserId(userId);
        List<Integer> recentPaperIds = histories.stream()
                .sorted(Comparator.comparing(History::getViewTime).reversed())
                .limit(10)
                .map(History::getPaperId)
                .collect(Collectors.toList());


        List<RecommendationVO> recommendations = new ArrayList<>();
        for (Integer paperId : recentPaperIds) {
            List<SimilarPaper> similarPapers = similarPaperRepository.findByPaperId(paperId);
            for (SimilarPaper similarPaper : similarPapers) {
                PaperVO paperVO = paperRepository.findByPaperId(similarPaper.getSimilarPaperId()).toVO();
                PaperVO historyPaperVO = paperRepository.findByPaperId(paperId).toVO();
                recommendations.add(new RecommendationVO(paperVO.getPaperId(), paperVO.getTitle(), paperVO.getAbstractText(), "Based on your recent browse history of " + historyPaperVO.getTitle(), similarPaper.getSimilarity()));
            }
        }

        return recommendations;
    }
}