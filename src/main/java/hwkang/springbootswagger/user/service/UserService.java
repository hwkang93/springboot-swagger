package hwkang.springbootswagger.user.service;

import hwkang.springbootswagger.user.data.UserDto;

import java.util.List;

public interface UserService {

    /**
     * 사용자 전체 목록 조회
     * @return List<UserDto>
     */
    List<UserDto> findAll();

    /**
     * 사용자 ID 로 사용자 조회
     * @param userId
     * @return UserDto
     */
    UserDto findByUserId(long userId);

    /**
     * 사용자 추가
     * @param userDto
     * @return userDto
     */
    UserDto insertUser(UserDto userDto);

    /**
     * 사용자 삭제
     * @param userId
     */
    void deleteUser(long userId);
}
