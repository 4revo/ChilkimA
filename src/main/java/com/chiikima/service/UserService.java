package com.chiikima.service;

import com.chiikima.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;

@Service
public class UserService {
    private static final String USER_DIR = "user";
    private static final String USER_INFO_FILE = USER_DIR + "/user_info.json";
    private static final String USER_AVATAR_FILE = USER_DIR + "/avatar.png";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserService() {
        // 创建用户数据目录
        new File(USER_DIR).mkdirs();
    }

    public UserData loadUserData() {
        File file = new File(USER_INFO_FILE);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, UserData.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void saveUserData(UserData userData) {
        try {
            objectMapper.writeValue(new File(USER_INFO_FILE), userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAvatar(String avatarData) {
        if (avatarData != null && avatarData.contains(",")) {
            String[] parts = avatarData.split(",");
            byte[] imageData = Base64.getDecoder().decode(parts[1]);
            try {
                Files.write(Paths.get(USER_AVATAR_FILE), imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadAvatar() {
        Path path = Paths.get(USER_AVATAR_FILE);
        if (Files.exists(path)) {
            try {
                byte[] imageData = Files.readAllBytes(path);
                return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageData);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public String generateRandomName() {
        String baseName = "chiiki";
        StringBuilder randomName = new StringBuilder();
        Random random = new Random();
        
        for (char c : baseName.toCharArray()) {
            randomName.append(random.nextBoolean() ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        
        randomName.append(random.nextInt(900) + 100); // 添加100-999之间的随机数
        return randomName.toString();
    }

    public String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
} 