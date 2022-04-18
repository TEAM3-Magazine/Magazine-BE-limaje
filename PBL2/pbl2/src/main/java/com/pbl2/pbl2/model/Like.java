package com.pbl2.pbl2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity(name="post_like") // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Like {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long likeId;
}
