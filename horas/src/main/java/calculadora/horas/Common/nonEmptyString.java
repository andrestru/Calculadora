package calculadora.horas.Common;

public class nonEmptyString {

    private final String value;

    public nonEmptyString(String value) {
        validate.notNull(value, "NonEmpty cannot be null");
        validate.isTrue(validate.nonBlank(value), "NonEmpty cannot be empty");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NonEmptyString{" +
                "value='" + value + '\'' +
                '}';
    }
}
