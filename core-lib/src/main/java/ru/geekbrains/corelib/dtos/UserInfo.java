package ru.geekbrains.corelib.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("")
public class UserInfo {
    private Long userId;
    private String login;
    private List<String> roles;
}

