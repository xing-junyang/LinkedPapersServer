package org.bigdata.bigdatabackend.serviceImpl;

import org.bigdata.bigdatabackend.po.History;
import org.bigdata.bigdatabackend.po.Paper;
import org.bigdata.bigdatabackend.po.SimilarPaper;
import org.bigdata.bigdatabackend.repository.HistoryRepository;
import org.bigdata.bigdatabackend.repository.PaperRepository;
import org.bigdata.bigdatabackend.repository.SimilarPaperRepository;
import org.bigdata.bigdatabackend.service.RecommendationService;
import org.bigdata.bigdatabackend.vo.RecommendationVO;
import org.bigdata.bigdatabackend.vo.SimilarPaperVO;
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

        Map<Integer, Map.Entry<Integer, Double>> paperSimilarityMap = new HashMap<>();
        for (Integer paperId : recentPaperIds) {
            SimilarPaper similarPaper = similarPaperRepository.findByPaperId(paperId);
            SimilarPaperVO similarPaperVO = similarPaper.toVO();
            for (int i = 0; i < similarPaperVO.getSimilarPapers().size(); i++) {
                paperSimilarityMap.put(paperId, Map.entry(similarPaperVO.getSimilarPapers().get(i).getPaperId(), similarPaperVO.getSimilarPapers().get(i).getSimilarity()));
            }
        }

        return paperSimilarityMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Map.Entry<Integer, Double>>comparingByValue(Comparator.comparing(Map.Entry::getValue)).reversed())
                .map(entry -> {
                    var paper = paperRepository.findById(entry.getKey()).orElse(null);
                    if (paper != null) {
                        return new RecommendationVO(paper.getPaperId(), paper.getTitle(), paper.getAbstractText(),
                                "This paper is similar to paper ID: " + entry.getValue().getKey(), entry.getValue().getValue());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}