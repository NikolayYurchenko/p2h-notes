package com.p2h.notes.controller;

import com.p2h.notes.model.UserAuthResponse;
import com.p2h.notes.model.UserPrincipal;
import com.p2h.notes.model.UserRequest;
import com.p2h.notes.model.UserResponse;
import com.p2h.notes.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@Api(tags = "Users API")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    @SneakyThrows
    @ApiOperation("Register user")
    public UserAuthResponse register(@RequestBody @Valid UserRequest request) {

        log.info("Request for create user from data:[{}]", request);

        return userService.register(request);
    }

    @PostMapping("/login")
    @SneakyThrows
    @ApiOperation("Login user")
    public UserAuthResponse login(@RequestParam @NotBlank String secretKey) {

        log.info("Request for login user by secret key:[{}]", secretKey);

        return userService.login(secretKey);
    }

    @GetMapping("/whoami")
    @ApiOperation("Find info about me")
    public UserResponse findMe(@AuthenticationPrincipal UserPrincipal principal) {

        log.info("Request for find user by uuid:[{}]. User:[{}]", principal.getUserUid(), principal.getUserUid());

        return userService.whoami(principal.getUserUid());
    }

    @PutMapping
    @ApiOperation("Update user information")
    public UserResponse update(@RequestBody @Valid UserRequest userRequest, @AuthenticationPrincipal UserPrincipal principal ) {

        log.info("Request for update user by uuid:[{}]", principal.getUserUid());

        return userService.update(principal.getUserUid(), userRequest);
    }

    @DeleteMapping("/{userUid}")
    @ApiOperation("Delete user")
    public void delete(@PathVariable @NotBlank String userUid) {

        userService.delete(userUid);
    }
}
