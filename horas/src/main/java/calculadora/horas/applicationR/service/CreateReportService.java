package calculadora.horas.applicationR.service;

import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.applicationR.errors.InputDataError;
import calculadora.horas.applicationR.model.CreateReportRequest;
import calculadora.horas.applicationR.model.CreateReportResponse;
import calculadora.horas.applicationR.ports.in.CreateReportUseCase;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import calculadora.horas.common.NonEmptyString;
import calculadora.horas.common.Validate;



public class CreateReportService implements CreateReportUseCase {

    private final ReportRepository repository;

    public CreateReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public CreateReportResponse execute(CreateReportRequest request) {
        Report report = validate(request);
        repository.save(report);
        return new CreateReportResponse(report);
    }

    private Report validate(CreateReportRequest request){
        try {
            NonEmptyString identificationT = new NonEmptyString(request.getIdentificationT());
            NonEmptyString identificationS = new NonEmptyString(request.getIdentificationS());
            NonEmptyString startDate = new NonEmptyString(request.getStartDate());
            NonEmptyString finalDate = new NonEmptyString(request.getFinalDate());
            Validate.date(startDate, finalDate, "Finish date cannot be less than start date");
            return new Report(
                    identificationT,
                    identificationS,
                    startDate,
                    finalDate
            );
        }catch (Exception e) {
            throw new InputDataError(e.getMessage());
        }
    }


}
