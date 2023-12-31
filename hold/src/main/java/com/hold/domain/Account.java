package com.hold.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @Builder @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String phoneNum;

    private Authority authority; // 유저 타입

    private boolean emailVerified; // 이메일 인증

    private  String emailCheckToken;

    private LocalDateTime joinedAt;

    // + 추가 정보
    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private Integer weight;

    @ManyToMany
    private Set<Disease> diseases = new HashSet<>(); // 질병

    @ManyToMany
    private Set<Medicine> medicines = new HashSet<>(); // 약

    @ManyToMany
    private Set<Nutrient> nutrients = new HashSet<>(); // 영양제

}
