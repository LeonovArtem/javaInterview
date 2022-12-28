package al.spring.controller;

import al.spring.service.UserLike;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
@RequiredArgsConstructor
public class UserLikeController {
    private final UserLike userLike;

    @GetMapping
    public void like(int userId) {
        userLike.increaseLikeByTransaction(userId);
    }

    @GetMapping("rabbit")
    public void increaseLike(int userId) {
        userLike.publish(userId);
    }
}
