package org.bigdata.bigdatabackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "citations", indexes = {
        @Index(name = "idx_citation_id", columnList = "citation_id"),
        @Index(name = "idx_title", columnList = "title")
})
public class Citation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citation_id", nullable = false, unique = true)
    private Integer citationId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "year")
    private int year;


    @Basic
    @JoinColumn(name = "paper_id")
    private Integer paperId;
}