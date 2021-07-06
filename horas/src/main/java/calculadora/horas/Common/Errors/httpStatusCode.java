package calculadora.horas.Common.Errors;

public enum httpStatusCode {
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    httpStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
