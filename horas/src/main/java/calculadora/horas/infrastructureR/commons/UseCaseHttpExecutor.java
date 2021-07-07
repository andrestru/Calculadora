package calculadora.horas.infrastructureR.commons;


import calculadora.horas.common.operation.ApplicationRequest;
import calculadora.horas.common.operation.ApplicationResponse;
import calculadora.horas.common.operation.ApplicationUseCase;
import calculadora.horas.common.errors.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UseCaseHttpExecutor {

    public <IN extends ApplicationRequest, OUT extends ApplicationResponse>ResponseEntity execute(
            ApplicationUseCase<IN,OUT> useCase, IN ApplicationRequest
    ) {
        try {
            OUT response = useCase.execute(ApplicationRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ApplicationError applicationError) {
            int httpcode = applicationError.httpstatuscode().getCode();
            Map<String, Object> body = Map.of(
                    "Message", applicationError.getMessage(),
                    "ErrorCode", applicationError.errorCode(),
                    "Metadata", applicationError.metadata()
            );
            return ResponseEntity.status(httpcode).body(body);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
