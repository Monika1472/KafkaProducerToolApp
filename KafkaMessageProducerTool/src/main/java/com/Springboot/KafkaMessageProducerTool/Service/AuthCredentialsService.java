package com.Springboot.KafkaMessageProducerTool.Service;

import com.Springboot.KafkaMessageProducerTool.Entity.AuthCredentials;
import com.Springboot.KafkaMessageProducerTool.Repo.AuthCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCredentialsService {
    @Autowired
    private AuthCredentialsRepo authCredentialsRepo;

    public String validateLogin(String username, String password, String role) {
        AuthCredentials user = authCredentialsRepo.findByUsernameAndPassword(username, password);

        if (user != null && user.getRole().equalsIgnoreCase(role)) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }
    public int getLoginId(String username) {
        AuthCredentials user = authCredentialsRepo.findByUsername(username);
        return (user != null) ? user.getLoginid() : -1;
    }
    public boolean existsByUsername(String username) {
        return authCredentialsRepo.existsByUsername(username);
    }

    public void saveUserCredentials(AuthCredentials credentials) {
        authCredentialsRepo.save(credentials);
    }
}
