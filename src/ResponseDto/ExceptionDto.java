package ResponseDto;

import com.google.gson.annotations.SerializedName;

public class ExceptionDto {
    final String message;

    public ExceptionDto(String message) {
        this.message = message;
    }
}

