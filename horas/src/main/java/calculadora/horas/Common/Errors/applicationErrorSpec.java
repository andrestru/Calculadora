package calculadora.horas.Common.Errors;

import java.util.Map;

public interface applicationErrorSpec {
    String errorCode();

    httpStatusCode httpstatuscode();

    String getMessage();

    default Map<String, Object> metadata() {return Map.of();}
}
