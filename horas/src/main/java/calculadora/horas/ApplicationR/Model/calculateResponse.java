package calculadora.horas.ApplicationR.Model;

import calculadora.horas.ApplicationR.Domain.report;
import calculadora.horas.Common.Operation.applicationResponse;

import java.util.Collection;

public class calculateResponse implements applicationResponse {

    private final Collection<report> calculate;

    public calculateResponse(Collection<report> calculate) {
        this.calculate = calculate;
    }

    public Collection<report> getCalculate() {
        return calculate;
    }
}
