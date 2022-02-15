package hwkang.springbootswagger.user.service;

import hwkang.springbootswagger.user.data.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUserList();

    UserDto findByUserId(long userId);

    UserDto insertUser(UserDto userDto);

    void deleteUser(long userId);
}
