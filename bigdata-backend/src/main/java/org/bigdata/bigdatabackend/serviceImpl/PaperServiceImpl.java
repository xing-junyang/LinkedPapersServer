package org.bigdata.bigdatabackend.serviceImpl;

import org.bigdata.bigdatabackend.enums.SortByEnum;
import org.bigdata.bigdatabackend.exception.BigDataException;
import org.bigdata.bigdatabackend.po.Paper;
import org.bigdata.bigdatabackend.repository.CitationRepository;
import org.bigdata.bigdatabackend.repository.PaperRepository;
import org.bigdata.bigdatabackend.repository.SimilarPaperRepository;
import org.bigdata.bigdatabackend.service.PaperService;
import org.bigdata.bigdatabackend.vo.PaperFilterVO;
import org.bigdata.bigdatabackend.vo.PaperVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private CitationRepository citationRepository;

    @Autowired
    private SimilarPaperRepository similarPaperRepository;

    @Override
    public PaperVO getPaperInfoVip(Integer paperId) {
        Paper paper = paperRepository.findById(paperId).orElseThrow(() -> BigDataException.paperNotFound());
        return convertToPaperVO(paper, true);
    }

    @Override
    public PaperVO getPaperInfoNormal(Integer paperId) {
        Paper paper = paperRepository.findById(paperId).orElseThrow(() -> BigDataException.paperNotFound());
        return convertToPaperVO(paper, false);
    }

    @Override
    public List<PaperVO> getPapersByFilter(PaperFilterVO filterVO) {
        List<Paper> papers = paperRepository.findAll();
        return papers.stream()
                .filter(paper -> filterVO.getKeyword() == null || paper.getTitle().contains(filterVO.getKeyword()))
                .filter(paper -> filterVO.getCategory() == null || paper.getCategory().equals(filterVO.getCategory()))
                .filter(paper -> filterVO.getYear_floor() == 0 || paper.getYear() >= filterVO.getYear_floor())
                .filter(paper -> filterVO.getYear_ceil() == 0 || paper.getYear() <= filterVO.getYear_ceil())
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
            paperVO.setCitations(citationRepository.findByPaperId(paper.getPaperId()).stream()
                    .map(citation -> citation.toVO())
                    .collect(Collectors.toList()));
            paperVO.setSimilarPapers(similarPaperRepository.findByPaperId(paper.getPaperId()).stream()
                    .map(similarPaper -> similarPaper.toVO())
                    .collect(Collectors.toList()));
        }
        return paperVO;
    }
}