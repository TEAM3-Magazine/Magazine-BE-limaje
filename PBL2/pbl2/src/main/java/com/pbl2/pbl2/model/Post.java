package com.pbl2.pbl2.model;

import com.pbl2.pbl2.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String contents;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;
//
//    @Column(nullable = false)
//    private String title;



    public Post(PostDto.Request request){
        this.contents = request.getContents();
        this.imageUrl = request.getImage_url();
    }

    public void update(PostDto.Request request) {
        this.contents = request.getContents();
        this.imageUrl = request.getImage_url();
    }
}