package calculadora.horas.ApplicationR.Model;

import calculadora.horas.Common.Operation.applicationRequest;

import java.util.Objects;

public class calculateRequest implements applicationRequest {
    private String identificationT;

    public calculateRequest() {
    }

    public calculateRequest(String identificationT) {
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
        calculateRequest that = (calculateRequest) o;
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
