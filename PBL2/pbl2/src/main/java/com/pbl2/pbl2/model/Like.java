package com.pbl2.pbl2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity(name="post_like") // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Like {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long likeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public void setUser(User user) { this.user = user; }
    public void setPost(Post post) { this.post = post; }
}
