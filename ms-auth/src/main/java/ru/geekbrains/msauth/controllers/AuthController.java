package ru.geekbrains.msauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.corelib.configurations.rediseBean.RediseDB;
import ru.geekbrains.corelib.repository.RedisRepository;
import ru.geekbrains.msauth.dtos.AuthRequestDto;
import ru.geekbrains.msauth.dtos.AuthResponseDto;
import ru.geekbrains.msauth.dtos.UserDto;
import ru.geekbrains.msauth.entities.User;
import ru.geekbrains.msauth.service.JwtProducer;
import ru.geekbrains.msauth.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProducer jwtProducer;

    @Autowired
    private RedisRepository redisRepository;

    @PostMapping("/signup")
    public HttpStatus registerUser(@RequestBody AuthRequestDto signUpRequest) {
        UserDto user = new UserDto();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(signUpRequest.getPassword());
        if(userService.saveUser(user)!=null)
            return HttpStatus.OK;
        return HttpStatus.valueOf(401);
    }

    @PostMapping("/login")
    public AuthResponseDto auth(@RequestBody AuthRequestDto request) {
        UserDto user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProducer.generateToken(user);
        return new AuthResponseDto(token);
    }

    @GetMapping("/logout")
    public Boolean logout(@RequestHeader("Authorization") String token) {
        redisRepository.saveToken(token,3600);
        return true;
    }


}
