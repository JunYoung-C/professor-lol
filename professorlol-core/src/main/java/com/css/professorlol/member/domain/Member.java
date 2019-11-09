package com.css.professorlol.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private MemberType memberType;

    @OneToMany(mappedBy = "member")
    private List<Subscribe> subscribes;
}
