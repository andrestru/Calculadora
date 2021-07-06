package calculadora.horas.ApplicationR.Service;


import calculadora.horas.ApplicationR.Domain.report;
import calculadora.horas.ApplicationR.Model.calculateRequest;
import calculadora.horas.ApplicationR.Model.calculateResponse;
import calculadora.horas.ApplicationR.Ports.In.calculateUseCase;
import calculadora.horas.ApplicationR.Ports.Out.reportRepository;
import calculadora.horas.Common.nonEmptyString;

import java.util.Collection;

public class calculateService implements calculateUseCase {

    private final reportRepository calculateRepository;

    public calculateService(reportRepository calculateRepository) {
        this.calculateRepository = calculateRepository;
    }

    @Override
    public calculateResponse execute(calculateRequest request) {
        Collection<report> calculate = calculateRepository.getById(new nonEmptyString(request.getIdentificationT()));
        return new calculateResponse(calculate);
    }

}
