package hwkang.springbootswagger.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SuccessResponse<T> extends BasicResponse {

    private T data;
    private int count = 1;

    public SuccessResponse(T data) {
        this.data = data;

        if(data instanceof List) {
            this.count = ((List<?>) data).size();
        }
    }
}
