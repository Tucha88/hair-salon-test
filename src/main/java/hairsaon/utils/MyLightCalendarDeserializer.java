package hairsaon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import hairsaon.myExtends.LightCalendar;

import java.io.IOException;

/**
 * Created by Boris on 24.05.2017.
 */
public class MyLightCalendarDeserializer extends KeyDeserializer {
    @Override
    public LightCalendar deserializeKey(String s, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return new LightCalendar(s);
    }
//    @Override
//    public LightCalendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        ObjectCodec oc = jsonParser.getCodec();
//        JsonNode node = oc.readTree(jsonParser);
//        return new LightCalendar(node.get("yearLight").asInt(),node.get("monthLight").asInt(),node.get("dayLight").asInt());
//    }
}
