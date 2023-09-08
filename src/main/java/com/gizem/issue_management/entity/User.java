package com.gizem.issue_management.entity;

//import jakarta.persistence.*;
import lombok.*;
import javax.persistence.*;


import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper=false)
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", length = 100, unique = true)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "name_surname", length = 200)
    private String nameSurname;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @JoinColumn(name = "assignee_user_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Issue> issues;
}
