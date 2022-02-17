package hwkang.springbootswagger.user.service.impl;

import hwkang.springbootswagger.user.data.UserDto;
import hwkang.springbootswagger.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<UserDto> userDtoList = new ArrayList<>();

    @PostConstruct
    public void init() {
        userDtoList.add(new UserDto(1, "khw", 30));
    }

    @Override
    public List<UserDto> findAll() {
        return this.userDtoList;
    }

    @Override
    public UserDto findByUserId(long userId) {
        return userDtoList.stream()
                .filter(userDto -> userDto.getUserId() == userId)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
        long max = userDtoList.stream()
                .map(o -> o.getUserId())
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(0L);

        userDto.setUserId(max + 1);

        userDtoList.add(userDto);

        return userDto;
    }

    @Override
    public void deleteUser(long userId) {
        UserDto removeDto = userDtoList.stream()
                .filter(userResponseDto -> userResponseDto.getUserId() == userId)
                .findFirst()
                .orElseThrow();

        userDtoList.remove(removeDto);
    }
}
