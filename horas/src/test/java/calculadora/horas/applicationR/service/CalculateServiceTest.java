package calculadora.horas.applicationR.service;

import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.applicationR.model.CalculateRequest;
import calculadora.horas.applicationR.model.CalculateResponse;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import calculadora.horas.common.NonEmptyString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CalculateServiceTest {


    @Test
    void listById(){
        ReportRepository repository = Mockito.mock(ReportRepository.class);
        Collection<Report> collection = new ArrayList<>();
        Report report = new Report(
                new NonEmptyString("identificationT"),
                new NonEmptyString("identificationS"),
                new NonEmptyString("2020-01-01T04:20:00"),
                new NonEmptyString("2020-01-02T04:20:00")
        );
        collection.add(report);
        Mockito.when(repository.getById(any(NonEmptyString.class))).thenReturn(collection);
        CalculateService service = new CalculateService(repository);
        CalculateRequest request = new CalculateRequest("8");
        CalculateResponse response = service.execute(request);
        assertTrue(response.getCalculate().size()==1);
        assertDoesNotThrow(()->service.execute(request));
    }

}