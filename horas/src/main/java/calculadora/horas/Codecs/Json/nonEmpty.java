package calculadora.horas.Codecs.Json;


import calculadora.horas.Common.nonEmptyString;
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
public class nonEmpty {

    public static class nonEmptyEncoder extends JsonSerializer<nonEmptyString> {

        @Override
        public void serialize(nonEmptyString nonEmptyString, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(nonEmptyString.getValue());
        }
    }

    public static class nonEmptyDecoder extends JsonDeserializer<nonEmptyString> {

        @Override
        public nonEmptyString deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String value = jsonParser.getValueAsString();
            return new nonEmptyString(value);
        }
    }

}
