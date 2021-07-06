package calculadora.horas.ApplicationR.Errors;



import calculadora.horas.Common.Errors.applicationError;
import calculadora.horas.Common.Errors.httpStatusCode;

import java.util.Map;

public class externalSystemError extends applicationError {

    private final String message;
    private final Throwable throwable;

    public externalSystemError(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String errorCode() {
        return "EXTERNAL_SYSTEM_ERROR";
    }

    @Override
    public httpStatusCode httpstatuscode() {
        return httpstatuscode().INTERNAL_SERVER_ERROR;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "throwable", throwable
        );
    }
}