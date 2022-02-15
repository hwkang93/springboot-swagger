package hwkang.springbootswagger.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse extends BasicResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
