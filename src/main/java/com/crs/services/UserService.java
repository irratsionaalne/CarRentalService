package com.crs.services;

import com.crs.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    boolean createUser(User user) throws Exception;

    boolean updateUser(User user) throws Exception;

    List<User> getAllUsers();

    User getById(UUID userId);
}