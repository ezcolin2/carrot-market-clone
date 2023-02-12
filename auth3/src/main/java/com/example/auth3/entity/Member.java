package com.example.auth3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="user_pwd")
    private String userPwd;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
    mappedBy = "member")
    @Builder.Default
    private List<Post> post = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY,
    cascade=CascadeType.ALL,
    orphanRemoval = true,
    mappedBy = "member")
    @Builder.Default
    private List<Interest> interests = new ArrayList<>();

}
