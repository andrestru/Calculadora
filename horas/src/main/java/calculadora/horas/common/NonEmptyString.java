package calculadora.horas.common;

public class NonEmptyString {

    private final String value;

    public NonEmptyString(String value) {
        Validate.notNull(value, "NonEmpty cannot be null");
        Validate.isTrue(Validate.nonBlank(value), "NonEmpty cannot be empty");
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
