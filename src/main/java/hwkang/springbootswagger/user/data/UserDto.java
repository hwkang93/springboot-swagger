package hwkang.springbootswagger.user.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel
@Getter @Setter
@NoArgsConstructor
public class UserDto {

    @ApiModelProperty(value = "사용자 아이디")
    private long userId;

    @ApiModelProperty(value = "사용자 명")
    private String userName;

    @ApiModelProperty(value = "사용자 나이")
    private int age;

    @Builder
    public UserDto(long userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
