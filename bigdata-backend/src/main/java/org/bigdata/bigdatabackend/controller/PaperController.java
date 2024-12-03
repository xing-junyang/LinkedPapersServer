package org.bigdata.bigdatabackend.controller;

import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.po.User;
import org.bigdata.bigdatabackend.service.PaperService;
import org.bigdata.bigdatabackend.util.SecurityUtil;
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

    @Autowired
    SecurityUtil securityUtil;

    @GetMapping("/{paperId}")
    public ResultVO<PaperVO> getPaperInfo(@PathVariable Integer paperId) {
        User user = securityUtil.getCurrentUser();
        if(user.getRole().equals(RoleEnum.VIP)) {
            return ResultVO.buildSuccess(paperService.getPaperInfoVip(paperId));
        } else {
            return ResultVO.buildSuccess(paperService.getPaperInfoNormal(paperId));
        }
    }

    @PostMapping("/search")
    public ResultVO<List<PaperVO>> getPapersByFilter(@RequestBody PaperFilterVO filterVO) {
        return ResultVO.buildSuccess(paperService.getPapersByFilter(filterVO));
    }
}