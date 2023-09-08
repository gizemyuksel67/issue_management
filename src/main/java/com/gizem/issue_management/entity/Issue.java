package com.gizem.issue_management.entity;

//import jakarta.persistence.*;
import lombok.*;
import javax.persistence.*;


import java.util.Date;

@Data
@Entity // bu sınıf bır verıtabanı tablosudur
@Table(name = "issue") // tablonun ozellıklerı
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class Issue extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "detail", length = 200)
    private String detail;

    @Column(name = "date")
    private Date date;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @JoinColumn(name = "assignee_user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User assignee;

    @JoinColumn(name = "project_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Project project;

}
