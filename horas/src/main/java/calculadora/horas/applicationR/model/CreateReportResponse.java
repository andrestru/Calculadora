package calculadora.horas.applicationR.model;

import calculadora.horas.common.operation.ApplicationResponse;
import calculadora.horas.applicationR.domain.Report;

public class CreateReportResponse implements ApplicationResponse {

    private final Report report;

    public CreateReportResponse(Report report) {
        this.report = report;
    }

    public Report getReport() {
        return report;
    }

    @Override
    public String toString() {
        return "CreateReportResponse{" +
                "report=" + report +
                '}';
    }
}
