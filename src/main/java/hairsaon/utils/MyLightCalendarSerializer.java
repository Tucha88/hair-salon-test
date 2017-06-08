package hairsaon.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Boris on 24.05.2017.
 */
public class MyLightCalendarSerializer extends JsonSerializer<LightCalendar> {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public void serialize(LightCalendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, calendar);
        jsonGenerator.writeFieldName(writer.toString());

    }
}
