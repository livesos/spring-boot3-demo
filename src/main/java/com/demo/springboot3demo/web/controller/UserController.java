package com.demo.springboot3demo.web.controller;

import com.demo.springboot3demo.web.model.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lives@gamevector
 * @since 2024/6/10
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "用户信息")
public class UserController {

    @Operation(summary = "获取用户信息")
    @GetMapping("/{username}")
    public UserVO getUserInfo(@PathVariable("username") String username) {
        // 实现逻辑
        return new UserVO(username, "123456", "123456@qq.com");
    }


}
