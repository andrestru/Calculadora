package calculadora.horas.infrastructureR.adapters.in;


import calculadora.horas.applicationR.model.*;
import calculadora.horas.applicationR.ports.in.CalculateUseCase;
import calculadora.horas.applicationR.ports.in.CreateReportUseCase;
import calculadora.horas.infrastructureR.commons.UseCaseHttpExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/report")
public class ReportController {

    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateReportUseCase createReportUseCase;
    private final CalculateUseCase Calculate;

    public ReportController(UseCaseHttpExecutor useCaseHttpExecutor, CreateReportUseCase createReportUseCase, CalculateUseCase calculate) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createReportUseCase = createReportUseCase;
        Calculate = calculate;
    }

    @PostMapping
    public ResponseEntity createProduct(
            @RequestBody CreateReportRequest request
    ){
        return useCaseHttpExecutor.execute(
                createReportUseCase,
                request
        );
    }

    @PostMapping(path = {"/listid"})
    public ResponseEntity responseEntity(
            @RequestBody CalculateRequest request
    ){
        return useCaseHttpExecutor.execute(
                Calculate,
                request
        );
    }

    @PostMapping("/calculo/{fechas}")
    @ResponseBody
    public Object[] calcularHoras(@PathVariable Object[] fechas) {
        calculadora.horas.applicationR.model.Calculate calcularHoras = new Calculate();
        return calcularHoras.calcularhoras(fechas);

    }

}
