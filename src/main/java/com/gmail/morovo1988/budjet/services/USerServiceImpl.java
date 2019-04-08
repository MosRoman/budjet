package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.repositories.RoleRepository;
import com.gmail.morovo1988.budjet.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class USerServiceImpl implements UserService {


    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public USerServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(User user) {
        System.out.println(user);
        User newUser = user;
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.addRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(newUser);
        return user;
    }

    @Override
    public User loadUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> findUsers() {
        return this.userRepository.findAll();
    }
}
