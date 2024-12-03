package org.bigdata.bigdatabackend.repository;

import org.bigdata.bigdatabackend.po.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByUserId(Integer userId);
}