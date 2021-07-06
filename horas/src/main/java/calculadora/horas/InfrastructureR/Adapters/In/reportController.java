package calculadora.horas.InfrastructureR.Adapters.In;


import calculadora.horas.ApplicationR.Model.*;
import calculadora.horas.ApplicationR.Ports.In.calculateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/report")
public class reportController {

    private final calculadora.horas.InfrastructureR.Commons.useCaseHttpExecutor useCaseHttpExecutor;
    private final calculadora.horas.ApplicationR.Ports.In.createReportUseCase createReportUseCase;
    private final calculateUseCase Calculate;

    public reportController(calculadora.horas.InfrastructureR.Commons.useCaseHttpExecutor useCaseHttpExecutor, calculadora.horas.ApplicationR.Ports.In.createReportUseCase createReportUseCase, calculateUseCase calculate) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createReportUseCase = createReportUseCase;
        Calculate = calculate;
    }

    @PostMapping
    public ResponseEntity createProduct(
            @RequestBody createReportRequest request
    ){
        return useCaseHttpExecutor.execute(
                createReportUseCase,
                request
        );
    }

    @PostMapping(path = {"/listid"})
    public ResponseEntity responseEntity(
            @RequestBody calculateRequest request
    ){
        return useCaseHttpExecutor.execute(
                Calculate,
                request
        );
    }

    @PostMapping("/calculo/{fechas}")
    @ResponseBody
    public Object[] calcularHoras(@PathVariable Object[] fechas) {
        calculate calcularHoras = new calculate();
        return calcularHoras.calcularhoras(fechas);

    }

}
