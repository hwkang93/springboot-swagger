package hwkang.springbootswagger.user.controller;

import hwkang.springbootswagger.response.BasicResponse;
import hwkang.springbootswagger.response.ErrorResponse;
import hwkang.springbootswagger.response.SuccessResponse;
import hwkang.springbootswagger.user.data.UserDto;
import hwkang.springbootswagger.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(
            value = "사용자 목록 조회",
            notes = "사용자 전체 목록을 조회하는 기능입니다.",
            response = UserDto.class,
            responseContainer = "List"
    )
    @GetMapping("/users")
    public ResponseEntity<BasicResponse> findAll() {
        List<UserDto> userList = userService.findAll();

        return ResponseEntity.ok().body(new SuccessResponse<>(userList));
    }

    @ApiOperation(
            value = "사용자 검색",
            notes = "사용자 아이디로 사용자를 조회하는 기능입니다. <br> 사용자가 없을 시 NoSuchElementException 을 리턴합니다.",
            response = UserDto.class
    )
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", defaultValue = "1", dataType = "string", required = true)
    @GetMapping("/users/{userId}")
    public ResponseEntity<BasicResponse> findByUserId(@PathVariable String userId) {
        long paramUserId = Long.parseLong(userId);
        UserDto response = userService.findByUserId(paramUserId);

        return ResponseEntity.ok().body(new SuccessResponse<>(response));
    }

    @ApiOperation(
            value = "사용자 추가",
            notes = "사용자를 추가하는 기능입니다."
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "사용자 명", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "age", value = "나이", dataType = "int", paramType = "query", required = true)
    })
    @PostMapping(value = "/users")
    public ResponseEntity<Void> insertUser(@ApiIgnore UserDto userDto) {
        UserDto insertedUser = userService.insertUser(userDto);
        String location = "users/" + insertedUser.getUserId();

        return ResponseEntity.created(URI.create(location)).build();
    }

    @ApiOperation(
            value = "사용자 삭제",
            notes = "사용자를 삭제하는 기능입니다."
    )
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", defaultValue = "1", dataType = "string", required = true)
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        long paramUserId = Long.parseLong(userId);

        userService.deleteUser(paramUserId);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<BasicResponse> noSuchElementExceptionHandler(NoSuchElementException exception) {
        final String message = "검색 결과가 없습니다. 검색 조건을 확인해주세요.";

        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }
}
