package org.bigdata.bigdatabackend.repository;

import org.bigdata.bigdatabackend.po.Citation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitationRepository extends JpaRepository<Citation, Integer> {
    Citation findByPaperId(Integer paperId);
}