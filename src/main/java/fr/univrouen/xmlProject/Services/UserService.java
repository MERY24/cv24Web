package fr.univrouen.xmlProject.Services;

import fr.univrouen.xmlProject.Dtos.UserDto;
import fr.univrouen.xmlProject.Dtos.Responses.UserResponseDto;
import fr.univrouen.xmlProject.Entities.User;

import java.util.List;

public interface UserService {

    public UserResponseDto addUser(UserDto user);
    public User findUser(Long id);
    public List<User> findAllUser();
    public User updateUser();
    public User deleteUser();


}
