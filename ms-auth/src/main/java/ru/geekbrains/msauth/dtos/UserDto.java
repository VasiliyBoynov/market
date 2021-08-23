package ru.geekbrains.msauth.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.msauth.entities.User;


import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Component("")
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private List<String> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(r->r.getName()).collect(Collectors.toList());
    }
}
