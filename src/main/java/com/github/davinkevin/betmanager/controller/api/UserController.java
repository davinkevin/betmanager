package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 15/08/15 for betmanager
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @RequestMapping("me")
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }
}
