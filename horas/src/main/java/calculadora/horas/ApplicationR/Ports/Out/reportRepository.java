package calculadora.horas.ApplicationR.Ports.Out;

import calculadora.horas.ApplicationR.Domain.report;
import calculadora.horas.Common.nonEmptyString;

import java.util.Collection;

public interface reportRepository {

    void save(report report);

    Collection<report> getById(nonEmptyString Calculate);


}
