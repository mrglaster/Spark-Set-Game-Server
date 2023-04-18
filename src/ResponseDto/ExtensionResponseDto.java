package ResponseDto;

import com.google.gson.annotations.SerializedName;

public class ExtensionResponseDto {
    final boolean success;

    @SerializedName("exception")
    final ExceptionDto exceptionDto;

    public ExtensionResponseDto(boolean success, ExceptionDto exceptionDto) {
        this.success = success;
        this.exceptionDto = exceptionDto;
    }
}
