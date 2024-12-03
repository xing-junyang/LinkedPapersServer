package org.bigdata.bigdatabackend.repository;

import org.bigdata.bigdatabackend.po.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
    Paper findByPaperId(Integer paperId);
//    Page<Paper> findByCategory(String category);
    Page<Paper> findByCategory(String category, Pageable pageable);
}