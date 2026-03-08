package org.mrpo.lab1.services;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.User;
import org.mrpo.lab1.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User findById(long id) {return userRepository.findById(id).orElse(null);}

    public List<User> findAllUserUsers() {
        return userRepository.findAllByRoleId(2);
    }
}
