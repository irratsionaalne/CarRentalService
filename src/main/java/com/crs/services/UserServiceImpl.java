package com.crs.services;

import com.crs.models.Role;
import com.crs.models.User;
import com.crs.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(!optionalUser.isPresent()){
            throw new Exception("User with this email does not exist");
        }
        return optionalUser.get();
    }

    public boolean doesUserExist(String email){
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public boolean createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        if (user == null || !userRepo.existsById(user.getId())) {
            throw new Exception("Invalid updating for Customer");
        }
        userRepo.saveAndFlush(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getById(UUID userId) {
        return userRepo.getOne(userId);
    }
}
