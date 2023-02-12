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
    @Column(name="member_id")
    private Long id;
    @Column(name="member_email")
    private String memberEmail;
    @Column(name="member_pwd")
    private String memberPwd;
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
