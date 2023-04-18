package ResponseDto;

public class RegisterExtensionResponse extends ExtensionResponseDto {
    final String accessToken;
    final String nickname;


    public RegisterExtensionResponse(String accessToken, String nickname) {
        super(true, null);
        this.accessToken = accessToken;
        this.nickname = nickname;

    }
}