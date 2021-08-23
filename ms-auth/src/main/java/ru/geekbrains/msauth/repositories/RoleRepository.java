package ru.geekbrains.msauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.msauth.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
