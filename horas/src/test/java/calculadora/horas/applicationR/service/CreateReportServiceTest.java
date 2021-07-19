package calculadora.horas.applicationR.service;

import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.applicationR.model.CreateReportRequest;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import calculadora.horas.common.NonEmptyString;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CreateReportServiceTest {

    @Test
    void Calculate(){
        Report report = new Report(
                new NonEmptyString("identificationT"),
                new NonEmptyString("identificationS"),
                new NonEmptyString("2020-01-01T04:20:00"),
                new NonEmptyString("2020-01-02T04:20:00")
        );
        ReportRepository repository = Mockito.mock(ReportRepository.class);
        CreateReportService service = new CreateReportService(repository);
        CreateReportRequest request = new CreateReportRequest(
          report.getIdentificationT().getValue(),
          report.getIdentificationS().getValue(),
          report.getStartDate().getValue(),
          report.getFinalDate().getValue()
        );
        assertAll(
                ()->assertDoesNotThrow((()-> service.execute(request))),
                ()->Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(Report.class))
        );
    }


}