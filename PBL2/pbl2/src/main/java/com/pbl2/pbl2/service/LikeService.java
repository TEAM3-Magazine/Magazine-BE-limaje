package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.LikeDto;
import com.pbl2.pbl2.exception.ExistLike;
import com.pbl2.pbl2.exception.NotFoundLike;
import com.pbl2.pbl2.exception.NotFoundPost;
import com.pbl2.pbl2.exception.NotFoundUser;
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

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Like addlikes(Long userId, Long postId) {
        if (likeRepository.findByUser_UserIdAndPost_PostId(userId, postId).isPresent()) {
            throw new ExistLike();
        }
        User user = userRepository.findById(userId).orElseThrow(NotFoundUser::new);
        Post post = postRepository.findById(postId).orElseThrow(NotFoundPost::new);
//        Optional<Post> post = Optional.ofNullable(boardRepository.findById(boardId))
//                .orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
//        Optional<User> user = Optional.ofNullable(userRepository.findById(likesrequestDto.getUserId()))
//                .orElseThrow(() -> new CustomException(EMAIL_NOT_FOUND));
        Like like = new Like();
        post.addLikelist(like);
        user.addUsertoLike(like);
        likeRepository.save(like);
        return like;
    }

    @Transactional
    public void delete(Long userId, Long postId) {
        Like like = likeRepository.findByUser_UserIdAndPost_PostId(userId, postId).orElseThrow(NotFoundLike::new);
//        Optional<Likelist> likelist = Optional.ofNullable(likesRepository.findByUser_IdAndPost_Id(userId, postId))
//                .orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
        likeRepository.delete(like);
    }
}
