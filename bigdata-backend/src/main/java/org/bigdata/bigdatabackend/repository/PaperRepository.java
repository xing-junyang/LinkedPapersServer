package org.bigdata.bigdatabackend.repository;

import org.bigdata.bigdatabackend.po.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
    Paper findByPaperId(Integer paperId);
    List<Paper> findByCategory(String category);
}