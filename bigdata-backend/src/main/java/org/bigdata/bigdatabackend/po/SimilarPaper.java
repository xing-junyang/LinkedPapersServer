package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "similar_papers", indexes = {
        @Index(name = "idx_similar_paper_id", columnList = "similar_paper_id"),
        @Index(name = "idx_title", columnList = "title")
})
public class SimilarPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "similar_paper_id", nullable = false, unique = true)
    private Integer similarPaperId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "similarity")
    private double similarity;

    @Basic
    @JoinColumn(name = "paper_id")
    private Integer paperId;
}