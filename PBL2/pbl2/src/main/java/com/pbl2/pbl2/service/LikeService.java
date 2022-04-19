package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.LikeDto;
import com.pbl2.pbl2.model.Like;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.LikeRepository;
import com.pbl2.pbl2.repository.PostRepository;
import com.pbl2.pbl2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likesRepository;

    @Transactional
    public Like addlikes(Long userId, Long postId) {
        if (likesRepository.findByUser_UserIdAndPost_PostId(userId, postId).isPresent()) {
            throw new IllegalArgumentException("이미 좋아요 상태입니다.");
        }
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
//        Optional<Post> post = Optional.ofNullable(boardRepository.findById(boardId))
//                .orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
//        Optional<User> user = Optional.ofNullable(userRepository.findById(likesrequestDto.getUserId()))
//                .orElseThrow(() -> new CustomException(EMAIL_NOT_FOUND));
        Like like = new Like();
        post.addLikelist(like);
        user.addUsertoLike(like);
        return like;
    }

    @Transactional
    public void delete(Long userId, Long postId) {
        Like like = likesRepository.findByUser_UserIdAndPost_PostId(userId, postId).orElseThrow(
                () -> new IllegalArgumentException("좋아요 목록이 존재하지 않습니다.")
        );
//        Optional<Likelist> likelist = Optional.ofNullable(likesRepository.findByUser_IdAndPost_Id(userId, postId))
//                .orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
        likesRepository.delete(like);
    }
}
