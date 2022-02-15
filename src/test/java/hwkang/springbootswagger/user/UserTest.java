package hwkang.springbootswagger.user;

import hwkang.springbootswagger.user.data.UserDto;
import hwkang.springbootswagger.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    void 사용자_목록_조회_성공() {
        List<UserDto> userList = userService.getUserList();

        assertThat(userList.size()).isEqualTo(1);
    }
    @Test
    void 사용자_검색_성공() {
        UserDto userDto = userService.findByUserId(1);

        assertThat(userDto.getUserName()).isEqualTo("khw");
    }

    @Test
    void 사용자_추가_성공() {
        UserDto paramUser = UserDto.builder()
                .userName("chr")
                .age(30)
                .build();

        userService.insertUser(paramUser);

        List<UserDto> userDtoList = userService.getUserList();

        assertThat(userDtoList.size()).isEqualTo(2);
    }

    @Test
    void 사용자_삭제_성공() {
        long id = 1;
        UserDto userDto = userService.findByUserId(id);

        System.out.println(userDto);
        assertThat(userDto.getUserName()).isEqualTo("khw");

        userService.deleteUser(id);

        assertThrows(NoSuchElementException.class, () -> userService.findByUserId(id));
    }
}
