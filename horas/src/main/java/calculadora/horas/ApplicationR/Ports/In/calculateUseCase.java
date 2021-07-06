package calculadora.horas.ApplicationR.Ports.In;

import calculadora.horas.ApplicationR.Model.calculateRequest;
import calculadora.horas.ApplicationR.Model.calculateResponse;
import calculadora.horas.Common.Operation.applicationUseCase;

public interface calculateUseCase extends applicationUseCase<calculateRequest, calculateResponse> {
}
