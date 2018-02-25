package pl.ryszardszwajlik.twitter.transferObjects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateTimestampSerializer extends JsonSerializer<LocalDateTime>
{
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeString(String.valueOf(Timestamp.valueOf(localDateTime).getTime()));
    }
}
