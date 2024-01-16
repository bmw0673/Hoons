package com.hoons.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hoons.domain.dto.UserDto;
import com.hoons.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

	/*
	 * @GetMapping("/user")
	 * 
	 * @PreAuthorize("hasAnyRole('USER','ADMIN')") public ResponseEntity<UserDto>
	 * getMyUserInfo(HttpServletRequest request) { return
	 * ResponseEntity.ok(userService.getMyUserWithAuthorities()); }
	 * 
	 * @GetMapping("/user/{username}")
	 * 
	 * @PreAuthorize("hasAnyRole('ADMIN')") public ResponseEntity<UserDto>
	 * getUserInfo(@PathVariable String username) { return
	 * ResponseEntity.ok(userService.getUserWithAuthorities(username)); }
	 */
}