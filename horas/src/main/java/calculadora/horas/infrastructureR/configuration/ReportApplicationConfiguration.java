package calculadora.horas.infrastructureR.configuration;

import calculadora.horas.applicationR.ports.in.CreateReportUseCase;
import calculadora.horas.applicationR.ports.in.CalculateUseCase;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import calculadora.horas.applicationR.service.CreateReportService;
import calculadora.horas.applicationR.service.CalculateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ReportApplicationConfiguration {

    @Bean
    public CreateReportUseCase createReportService(ReportRepository repository){
        return new CreateReportService(repository);
    }

    @Bean
    public CalculateUseCase calculateUseCase(ReportRepository repository){
        return new CalculateService(repository);
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
