package calculadora.horas.ApplicationR.Errors;


import calculadora.horas.Common.Errors.applicationError;
import calculadora.horas.Common.Errors.httpStatusCode;

public class inputDataError extends applicationError {

    private final String message;

    public inputDataError(String message) {
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
    public httpStatusCode httpstatuscode() {
        return httpstatuscode().BAD_REQUEST;
    }
}
