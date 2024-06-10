package com.demo.springboot3demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lives@gamevector
 * @since 2024/6/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户VO")
public class UserVO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "邮箱")
    private String email;

}
