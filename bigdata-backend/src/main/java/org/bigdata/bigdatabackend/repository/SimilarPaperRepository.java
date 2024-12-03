package org.bigdata.bigdatabackend.repository;

import org.bigdata.bigdatabackend.po.SimilarPaper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimilarPaperRepository extends JpaRepository<SimilarPaper, Integer> {
    SimilarPaper findByPaperId(Integer paperId);
}