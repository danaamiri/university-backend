package com.db.education.User;

import com.db.education.User.entity.User;
import com.db.education.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getCurrentUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principle instanceof UserDetails) {
            username = ((UserDetails) principle).getUsername();
        } else {
            username = principle.toString();
        }


        User user = userRepository.findByStudentNumber(username).orElseThrow(()-> new RuntimeException());
        return user;
    }
}
