package calculadora.horas.applicationR.domain;

import calculadora.horas.common.NonEmptyString;
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

        Executable operation = ()->new Report(new NonEmptyString(identificationT), new NonEmptyString(identificationS), new NonEmptyString(startDate), new NonEmptyString(finalDate));

        assertThrows(IllegalArgumentException.class, operation);
    }

    @Test
    void ReportNull(){
        Executable operation = ()->new Report(new NonEmptyString(null), new NonEmptyString(null), new NonEmptyString(null), new NonEmptyString(null));
        assertThrows(NullPointerException.class, operation);
    }
}