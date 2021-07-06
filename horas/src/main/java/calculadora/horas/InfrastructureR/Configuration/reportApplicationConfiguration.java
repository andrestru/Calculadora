package calculadora.horas.InfrastructureR.Configuration;

import calculadora.horas.ApplicationR.Ports.In.createReportUseCase;
import calculadora.horas.ApplicationR.Ports.In.calculateUseCase;
import calculadora.horas.ApplicationR.Ports.Out.reportRepository;
import calculadora.horas.ApplicationR.Service.createReportService;
import calculadora.horas.ApplicationR.Service.calculateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class reportApplicationConfiguration {

    @Bean
    public createReportUseCase createReportService(reportRepository repository){
        return new createReportService(repository);
    }

    @Bean
    public calculateUseCase calculateUseCase(reportRepository repository){
        return new calculateService(repository);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/report").allowedOrigins("http://localhost:4200/").allowedMethods("GET", "POST","PUT", "DELETE").maxAge(3600);
            }
        };
    }

}
