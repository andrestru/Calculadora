package calculadora.horas.InfrastructureR.Commons;


import calculadora.horas.Common.Operation.applicationRequest;
import calculadora.horas.Common.Operation.applicationResponse;
import calculadora.horas.Common.Operation.applicationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class useCaseHttpExecutor {

    public <IN extends applicationRequest, OUT extends applicationResponse>ResponseEntity execute(
            applicationUseCase<IN,OUT> useCase, IN ApplicationRequest
    ) {
        try {
            OUT response = useCase.execute(ApplicationRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (calculadora.horas.Common.Errors.applicationError applicationError) {
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
