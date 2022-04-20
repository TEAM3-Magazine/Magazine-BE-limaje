package com.pbl2.pbl2.model;

import com.pbl2.pbl2.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "user_id")
    private Long userId;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @OneToMany(mappedBy = "user")
    List<Post> posts = new ArrayList<>();


    public void addUserToPost(Post post) { post.setUser(this); }

    public void addUsertoLike(Like like) { like.setUser(this);}

    public User(UserDto.Request request) {
        this.userEmail = request.getUser_email();
        this.userName = request.getUser_name();
        this.userPassword = request.getUser_password();
    }

    public User(String fail){
        this.userName = "x";
    }

}