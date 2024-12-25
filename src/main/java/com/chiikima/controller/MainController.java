package com.chiikima.controller;

import com.chiikima.model.UserData;
import com.chiikima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/user_data")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserData() {
        Map<String, Object> response = new HashMap<>();
        UserData userData = userService.loadUserData();

        if (userData == null) {
            userData = new UserData();
            userData.setName(userService.generateRandomName());
            userData.setJoinTime(userService.getCurrentTime());
            userService.saveUserData(userData);
        }

        response.put("user_info", userData);
        response.put("avatar", userService.loadAvatar());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/user_data")
    @ResponseBody
    public ResponseEntity<Map<String, String>> saveUserData(@RequestBody UserData userData) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.saveUserData(userData);
            if (userData.getAvatar() != null) {
                userService.saveAvatar(userData.getAvatar());
            }
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
} 