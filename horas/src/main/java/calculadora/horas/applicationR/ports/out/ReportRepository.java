package calculadora.horas.applicationR.ports.out;

import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.common.NonEmptyString;

import java.util.Collection;

public interface ReportRepository {

    void save(Report report);

    Collection<Report> getById(NonEmptyString Calculate);


}
