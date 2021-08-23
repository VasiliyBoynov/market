package ru.geekbrains.msauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.geekbrains.msauth.dtos.UserDto;
import ru.geekbrains.msauth.entities.Role;
import ru.geekbrains.msauth.entities.User;
import ru.geekbrains.msauth.repositories.RoleRepository;
import ru.geekbrains.msauth.repositories.UserRepository;


import java.util.Collections;
import java.util.Date;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDto user) {

        if (findByLogin(user.getLogin())==null) {
            Role role = roleRepository.findByName("ROLE_USER");
            Date data = new Date();
            User u = new User();
            u.setLogin(user.getLogin());
            u.setDataCreate(data);
            u.setDataUpdate(data);
            u.setRoles(Collections.singletonList(role));
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(u);
        }
        return null;
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public UserDto findByEmail(String email) {

        return new UserDto(userRepository.findByEmail(email));
    }

    public UserDto findByLoginAndPassword(String login, String password) {
        User userEntity = userRepository.findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return new UserDto(userEntity);
            }
        }
        return null;
    }
}
