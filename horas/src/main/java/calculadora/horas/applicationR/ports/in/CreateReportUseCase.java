package calculadora.horas.applicationR.ports.in;

import calculadora.horas.applicationR.model.CreateReportRequest;
import calculadora.horas.applicationR.model.CreateReportResponse;
import calculadora.horas.common.operation.ApplicationUseCase;

public interface CreateReportUseCase extends ApplicationUseCase<CreateReportRequest, CreateReportResponse> {
}
