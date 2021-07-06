package calculadora.horas.ApplicationR.Service;

import calculadora.horas.ApplicationR.Domain.report;
import calculadora.horas.ApplicationR.Errors.inputDataError;
import calculadora.horas.ApplicationR.Model.createReportRequest;
import calculadora.horas.ApplicationR.Model.createReportResponse;
import calculadora.horas.ApplicationR.Ports.In.createReportUseCase;
import calculadora.horas.ApplicationR.Ports.Out.reportRepository;
import calculadora.horas.Common.nonEmptyString;
import calculadora.horas.Common.validate;



public class createReportService implements createReportUseCase {

    private final reportRepository repository;

    public createReportService(reportRepository repository) {
        this.repository = repository;
    }

    public createReportResponse execute(createReportRequest request) {
        report report = validate(request);
        repository.save(report);
        return new createReportResponse(report);
    }

    private report validate(createReportRequest request){
        try {
            nonEmptyString identificationT = new nonEmptyString(request.getIdentificationT());
            nonEmptyString identificationS = new nonEmptyString(request.getIdentificationS());
            nonEmptyString startDate = new nonEmptyString(request.getStartDate());
            nonEmptyString finalDate = new nonEmptyString(request.getFinalDate());
            validate.date(startDate, finalDate, "Finish date cannot be less than start date");
            return new report(
                    identificationT,
                    identificationS,
                    startDate,
                    finalDate
            );
        }catch (Exception e) {
            throw new inputDataError(e.getMessage());
        }
    }


}
