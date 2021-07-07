package calculadora.horas.applicationR.service;


import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.applicationR.model.CalculateRequest;
import calculadora.horas.applicationR.model.CalculateResponse;
import calculadora.horas.applicationR.ports.in.CalculateUseCase;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import calculadora.horas.common.NonEmptyString;

import java.util.Collection;

public class CalculateService implements CalculateUseCase {

    private final ReportRepository calculateRepository;

    public CalculateService(ReportRepository calculateRepository) {
        this.calculateRepository = calculateRepository;
    }

    @Override
    public CalculateResponse execute(CalculateRequest request) {
        Collection<Report> calculate = calculateRepository.getById(new NonEmptyString(request.getIdentificationT()));
        return new CalculateResponse(calculate);
    }

}
