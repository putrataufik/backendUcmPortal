package com.ucmportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ucmportal.dto.LoginDto;
import com.ucmportal.dto.UserSummaryDto;
import com.ucmportal.entities.User;
import com.ucmportal.repositories.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) { // register passoword sudah bisa di encode, untuk buat ini harus ada class
                                  // SecurityConfig
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepo.save(user);
    }

    // kebutuhan login
    public LoginResponse loginUser(LoginDto loginDto) {
        User user = userRepo.findByName(loginDto.getName());
        if (user != null) {
            boolean isPwdRight = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
            if (isPwdRight) {
                String userLevel = user.getLevel();
                if ("admin".equals(userLevel)) {
                    UserSummaryDto userSummary = new UserSummaryDto(user.getName(), userLevel, user.getMajor());
                    return new LoginResponse("Login successful. User level: admin", true, userSummary);
                } else {
                    UserSummaryDto userSummary = new UserSummaryDto(user.getName(), userLevel, user.getMajor());
                    return new LoginResponse("Login successful. User level: user", true, userSummary);
                }
            } else {
                return new LoginResponse("Incorrect password", false, null);
            }
        } else {
            return new LoginResponse("Name does not exist", false, null);
        }
    }
    

    public User findOne(Long nim) {
        return userRepo.findById(nim).get();
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void removeOne(long nim) {
        userRepo.deleteById(nim);
    }

    public List<User> findByName(String name) {
        return userRepo.findByNameContains(name);
    }

    public User getUserByUsername(String name) {
        return userRepo.findByName(name);
    }
}
