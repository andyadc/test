package com.bill99.ayf.auth.web.controller;

import com.bill99.ayf.auth.aspect.RequirePermission;
import com.bill99.ayf.auth.service.AuthService;
import com.bill99.ayf.auth.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * andy.an
 * 2020/3/25
 */
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/user/list")
    public Response userList() {
        return Response.success(authService.listUser());
    }

    @RequirePermission(value = "role:list")
    @GetMapping("/role/list")
    public Response roleList() {
        return Response.success(authService.listUser());
    }
}
