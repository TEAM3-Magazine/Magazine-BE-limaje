package com.pbl2.pbl2.model;

import com.pbl2.pbl2.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false, length = 1000)
    private String contents;

    @Column(name = "image_url", nullable = true, length = 1000)
    private String imageUrl;
//
//    @Column(nullable = false)
//    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<Like> likelist = new ArrayList<>();

    public void setUser(User user) { this.user = user; }

    public void addLikelist(Like like) { like.setPost(this); }


    public Post(User user, PostDto.Request request) {
        this.user = user;
        this.contents = request.getContents();
        this.imageUrl = request.getImage_url();
    }

    public void update(PostDto.Request request) {
        this.contents = request.getContents();
        this.imageUrl = request.getImage_url();
    }
}