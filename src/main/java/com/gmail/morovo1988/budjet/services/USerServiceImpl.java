package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.dto.requests.UpdateUserReq;
import com.gmail.morovo1988.budjet.exceptions.UserNotFoundException;
import com.gmail.morovo1988.budjet.repositories.RoleRepository;
import com.gmail.morovo1988.budjet.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.delete(this.userRepository.getOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UpdateUserReq> loadUpdateUserById(Long userId) {
        return this.userRepository.findUpdateUserReqById(userId);
    }

    @Override
    public User updateUser(UpdateUserReq updatedUser) {
        final User user = this.userRepository
                .findById(updatedUser.getId())
                .map(u -> {
                    u.setName(updatedUser.getName());
                    u.setEmail(updatedUser.getEmail());
                    u.setTelephone(updatedUser.getTelephone());
                    return u;
                }).orElseThrow(UserNotFoundException::new);
        return this.userRepository.save(user);
    }
}
