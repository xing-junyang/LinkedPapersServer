package org.bigdata.bigdatabackend.serviceImpl;

import org.bigdata.bigdatabackend.enums.SortByEnum;
import org.bigdata.bigdatabackend.exception.BigDataException;
import org.bigdata.bigdatabackend.po.Citation;
import org.bigdata.bigdatabackend.po.History;
import org.bigdata.bigdatabackend.po.Paper;
import org.bigdata.bigdatabackend.po.SimilarPaper;
import org.bigdata.bigdatabackend.repository.CitationRepository;
import org.bigdata.bigdatabackend.repository.HistoryRepository;
import org.bigdata.bigdatabackend.repository.PaperRepository;
import org.bigdata.bigdatabackend.repository.SimilarPaperRepository;
import org.bigdata.bigdatabackend.service.PaperService;
import org.bigdata.bigdatabackend.util.SecurityUtil;
import org.bigdata.bigdatabackend.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private CitationRepository citationRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private SimilarPaperRepository similarPaperRepository;

    @Autowired
    SecurityUtil securityUtil;

    @Override
    public PaperVO getPaperInfoVip(Integer paperId) {
        Paper paper = paperRepository.findById(paperId).orElseThrow(() -> BigDataException.paperNotFound());
        Integer userId = getCurrentUserId();
        recordUserHistory(userId, paperId, paper.getTitle());
        return convertToPaperVO(paper, true);
    }

    @Override
    public PaperVO getPaperInfoNormal(Integer paperId) {
        Paper paper = paperRepository.findById(paperId).orElseThrow(() -> BigDataException.paperNotFound());
        Integer userId = getCurrentUserId();
        recordUserHistory(userId, paperId, paper.getTitle());
        return convertToPaperVO(paper, false);
    }

    public Integer getCurrentUserId() {
        return securityUtil.getCurrentUser().getUserId();
    }


    @Override
    public List<PaperVO> getPapersByFilter(PaperFilterVO filterVO) {
        List<Paper> papers = paperRepository.findAll();

        return papers.stream()
                .filter(paper -> filterVO.getKeywords() == null || paper.getTitle().toLowerCase().contains(filterVO.getKeywords().toLowerCase()))
                .filter(paper -> Objects.equals(filterVO.getCategory(), "") || paper.getCategory().equals(filterVO.getCategory()))
                .filter(paper -> filterVO.getYearStart() == 0 || paper.getYear() >= filterVO.getYearStart())
                .filter(paper -> filterVO.getYearEnd() == 0 || paper.getYear() <= filterVO.getYearEnd())
                .sorted((p1, p2) -> {
                    if (filterVO.getSortBy() == SortByEnum.NEWEST) {
                        return Integer.compare(p2.getYear(), p1.getYear());
                    } else {
                        return p1.getTitle().compareTo(p2.getTitle());
                    }
                })
                .map(paper -> convertToPaperVO(paper, false))
                .collect(Collectors.toList());
    }

    private PaperVO convertToPaperVO(Paper paper, boolean isVip) {
        PaperVO paperVO = new PaperVO();
        paperVO.setPaperId(paper.getPaperId());
        paperVO.setTitle(paper.getTitle());
        paperVO.setAbstractText(paper.getAbstractText());
        paperVO.setYear(paper.getYear());
        paperVO.setCategory(paper.getCategory());
        if (isVip) {
//            paperVO.setCitations(citationRepository.findByPaperId(paper.getPaperId()).stream()
//                    .map(citation -> citation.toVO())
//                    .collect(Collectors.toList()));
//            paperVO.setSimilarPapers(similarPaperRepository.findByPaperId(paper.getPaperId()).stream()
//                    .map(similarPaper -> similarPaper.toVO())
//                    .collect(Collectors.toList()));
            SimilarPaper similarPaper = similarPaperRepository.findByPaperId(paper.getPaperId());
            String title0 = paperRepository.findByPaperId(similarPaper.getSimilarPaper0()).getTitle();
            String title1 = paperRepository.findByPaperId(similarPaper.getSimilarPaper1()).getTitle();
            String title2 = paperRepository.findByPaperId(similarPaper.getSimilarPaper2()).getTitle();
            String title3 = paperRepository.findByPaperId(similarPaper.getSimilarPaper3()).getTitle();
            String title4 = paperRepository.findByPaperId(similarPaper.getSimilarPaper4()).getTitle();
            String title5 = paperRepository.findByPaperId(similarPaper.getSimilarPaper5()).getTitle();
            String title6 = paperRepository.findByPaperId(similarPaper.getSimilarPaper6()).getTitle();
            String title7 = paperRepository.findByPaperId(similarPaper.getSimilarPaper7()).getTitle();
            String title8 = paperRepository.findByPaperId(similarPaper.getSimilarPaper8()).getTitle();
            String title9 = paperRepository.findByPaperId(similarPaper.getSimilarPaper9()).getTitle();
            SimilarPaperVO similarPaperVO = similarPaper.toVO();
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper0(), title0, similarPaper.getSimilarity0());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper1(), title1, similarPaper.getSimilarity1());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper2(), title2, similarPaper.getSimilarity2());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper3(), title3, similarPaper.getSimilarity3());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper4(), title4, similarPaper.getSimilarity4());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper5(), title5, similarPaper.getSimilarity5());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper6(), title6, similarPaper.getSimilarity6());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper7(), title7, similarPaper.getSimilarity7());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper8(), title8, similarPaper.getSimilarity8());
            similarPaperVO.addSimilarPapers(similarPaper.getSimilarPaper9(), title9, similarPaper.getSimilarity9());
            paperVO.setSimilarPapers(similarPaperVO);

            List<Paper> sameCategoryPaperList = paperRepository.findByCategory(paper.getCategory());
            List<SameCategoryPaperVO> sameCategoryPapers = sameCategoryPaperList.stream()
                    .filter(p -> !p.getPaperId().equals(paper.getPaperId()))
                    .map(p -> {
                        SameCategoryPaperVO sameCategoryPaperVO = new SameCategoryPaperVO();
                        sameCategoryPaperVO.setPaperId(p.getPaperId());
                        sameCategoryPaperVO.setTitle(p.getTitle());
                        return sameCategoryPaperVO;
                    })
                    .collect(Collectors.toList());
            paperVO.setSameCategoryPapers(sameCategoryPapers);
        }
        Citation citation = citationRepository.findByPaperId(paper.getPaperId());
        CitationVO citationVO = new CitationVO();
        if (citation.getCitations() != null && !citation.getCitations().isEmpty()) {
            List<CitationResultVO> citationList = Arrays.stream(citation.getCitations().split(","))
                    .map(citationId -> {
                        CitationResultVO citationResultVO = new CitationResultVO();
                        citationResultVO.setPaperId(Integer.parseInt(citationId));
                        Paper citedPaper = paperRepository.findById(Integer.parseInt(citationId)).orElse(null);
                        if (citedPaper != null) {
                            citationResultVO.setTitle(citedPaper.getTitle());
                            citationResultVO.setYear(citedPaper.getYear());
                        }
                        return citationResultVO;
                    })
                    .collect(Collectors.toList());
            citationVO.setCitations(citationList);
        }

        paperVO.setCitations(citationVO);
        return paperVO;
    }

    @Override
    public void recordUserHistory(Integer userId, Integer paperId, String title) {
        History history = new History();
        history.setUserId(userId);
        history.setPaperId(paperId);
        history.setTitle(title);
        history.setViewTime(new Date());
        historyRepository.save(history);
    }
}