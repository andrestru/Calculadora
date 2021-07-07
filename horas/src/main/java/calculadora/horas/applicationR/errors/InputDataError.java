package calculadora.horas.applicationR.errors;


import calculadora.horas.common.errors.ApplicationError;
import calculadora.horas.common.errors.HttpStatusCode;

public class InputDataError extends ApplicationError {

    private final String message;

    public InputDataError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String errorCode() {
        return "INPUT_DATA_ERROR";
    }

    @Override
    public HttpStatusCode httpstatuscode() {
        return httpstatuscode().BAD_REQUEST;
    }
}
