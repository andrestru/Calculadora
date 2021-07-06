package calculadora.horas.ApplicationR.Domain;

import calculadora.horas.Common.nonEmptyString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


class reportTest {

    @Test
    void Report(){
        String identificationT = " ";
        String identificationS = " ";
        String startDate = " ";
        String finalDate = " ";

        Executable operation = ()->new report(new nonEmptyString(identificationT), new nonEmptyString(identificationS), new nonEmptyString(startDate), new nonEmptyString(finalDate));

        assertThrows(IllegalArgumentException.class, operation);
    }

    @Test
    void ReportNull(){
        Executable operation = ()->new report(new nonEmptyString(null), new nonEmptyString(null), new nonEmptyString(null), new nonEmptyString(null));
        assertThrows(NullPointerException.class, operation);
    }
}