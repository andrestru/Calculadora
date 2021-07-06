package calculadora.horas.ApplicationR.Domain;

import calculadora.horas.Common.nonEmptyString;

public class report {
    nonEmptyString identificationT;
    nonEmptyString identificationS;
    nonEmptyString startDate;
    nonEmptyString finalDate;

    public report(nonEmptyString identificationT, nonEmptyString identificationS, nonEmptyString startDate, nonEmptyString finalDate) {
        this.identificationT = identificationT;
        this.identificationS = identificationS;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public nonEmptyString getIdentificationT() {
        return identificationT;
    }

    public nonEmptyString getIdentificationS() {
        return identificationS;
    }

    public nonEmptyString getStartDate() {
        return startDate;
    }

    public nonEmptyString getFinalDate() {
        return finalDate;
    }
}
