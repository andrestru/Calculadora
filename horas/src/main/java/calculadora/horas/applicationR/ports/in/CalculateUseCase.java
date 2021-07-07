package calculadora.horas.applicationR.ports.in;

import calculadora.horas.applicationR.model.CalculateRequest;
import calculadora.horas.applicationR.model.CalculateResponse;
import calculadora.horas.common.operation.ApplicationUseCase;

public interface CalculateUseCase extends ApplicationUseCase<CalculateRequest, CalculateResponse> {
}
