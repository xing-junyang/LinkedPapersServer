package org.bigdata.bigdatabackend.controller;

import org.bigdata.bigdatabackend.service.PaperService;
import org.bigdata.bigdatabackend.vo.PaperFilterVO;
import org.bigdata.bigdatabackend.vo.PaperVO;
import org.bigdata.bigdatabackend.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @GetMapping("/vip/{paperId}")
    public ResultVO<PaperVO> getPaperInfoVip(@PathVariable Integer paperId) {
        return ResultVO.buildSuccess(paperService.getPaperInfoVip(paperId));
    }

    @GetMapping("/normal/{paperId}")
    public ResultVO<PaperVO> getPaperInfoNormal(@PathVariable Integer paperId) {
        return ResultVO.buildSuccess(paperService.getPaperInfoNormal(paperId));
    }

    @PostMapping("/filter")
    public ResultVO<List<PaperVO>> getPapersByFilter(@RequestBody PaperFilterVO filterVO) {
        return ResultVO.buildSuccess(paperService.getPapersByFilter(filterVO));
    }
}