package calculadora.horas.applicationR.service;

import calculadora.horas.applicationR.model.CalculateRequest;
import calculadora.horas.applicationR.model.CalculateResponse;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {


    @Test
    void listById(){
        ReportRepository repository = Mockito.mock(ReportRepository.class);
        CalculateService service = new CalculateService(repository);
        CalculateRequest request = new CalculateRequest("8");
        CalculateResponse response = service.execute(request);
        assertDoesNotThrow(()->service.execute(request));
    }

}