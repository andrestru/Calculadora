package calculadora.horas.applicationR.model;

import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.common.operation.ApplicationResponse;

import java.util.Collection;

public class CalculateResponse implements ApplicationResponse {

    private final Collection<Report> calculate;

    public CalculateResponse(Collection<Report> calculate) {
        this.calculate = calculate;
    }

    public Collection<Report> getCalculate() {
        return calculate;
    }
}
