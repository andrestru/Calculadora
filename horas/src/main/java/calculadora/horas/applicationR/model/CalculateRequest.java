package calculadora.horas.applicationR.model;

import calculadora.horas.common.operation.ApplicationRequest;

import java.util.Objects;

public class CalculateRequest implements ApplicationRequest {
    private String identificationT;

    public CalculateRequest() {
    }

    public CalculateRequest(String identificationT) {
        this.identificationT = identificationT;
    }

    public String getIdentificationT() {
        return identificationT;
    }

    public void setIdentificationT(String identificationT) {
        this.identificationT = identificationT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateRequest that = (CalculateRequest) o;
        return Objects.equals(identificationT, that.identificationT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationT);
    }

    @Override
    public String toString() {
        return "calculateRequest{" +
                "identificationT='" + identificationT + '\'' +
                '}';
    }
}
