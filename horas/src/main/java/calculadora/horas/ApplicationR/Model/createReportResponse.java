package calculadora.horas.ApplicationR.Model;

import calculadora.horas.Common.Operation.applicationResponse;

public class createReportResponse implements applicationResponse {

    private final calculadora.horas.ApplicationR.Domain.report report;

    public createReportResponse(calculadora.horas.ApplicationR.Domain.report report) {
        this.report = report;
    }

    public calculadora.horas.ApplicationR.Domain.report getReport() {
        return report;
    }

    @Override
    public String toString() {
        return "CreateReportResponse{" +
                "report=" + report +
                '}';
    }
}
