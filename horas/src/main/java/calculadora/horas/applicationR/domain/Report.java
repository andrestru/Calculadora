package calculadora.horas.applicationR.domain;

import calculadora.horas.common.NonEmptyString;

public class Report {
    NonEmptyString identificationT;
    NonEmptyString identificationS;
    NonEmptyString startDate;
    NonEmptyString finalDate;

    public Report(NonEmptyString identificationT, NonEmptyString identificationS, NonEmptyString startDate, NonEmptyString finalDate) {
        this.identificationT = identificationT;
        this.identificationS = identificationS;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public NonEmptyString getIdentificationT() {
        return identificationT;
    }

    public NonEmptyString getIdentificationS() {
        return identificationS;
    }

    public NonEmptyString getStartDate() {
        return startDate;
    }

    public NonEmptyString getFinalDate() {
        return finalDate;
    }
}
