package calculadora.horas.codecs;


import calculadora.horas.common.NonEmptyString;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class NonEmpty {

    public static class nonEmptyEncoder extends JsonSerializer<NonEmptyString> {

        @Override
        public void serialize(NonEmptyString nonEmptyString, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(nonEmptyString.getValue());
        }
    }

    public static class nonEmptyDecoder extends JsonDeserializer<NonEmptyString> {

        @Override
        public NonEmptyString deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String value = jsonParser.getValueAsString();
            return new NonEmptyString(value);
        }
    }

}
