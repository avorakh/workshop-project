package dev.avorakh.workshop.geo.pub.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.avorakh.workshop.geo.pub.LocationData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class LocationDataSerializer extends StdSerializer<LocationData> {

    public LocationDataSerializer() {

        this(null);
    }

    public LocationDataSerializer(@Nullable Class<LocationData> vc) {

        super(vc);
    }

    @Override
    public void serialize(@NotNull LocationData value, @NotNull JsonGenerator gen, @Nullable SerializerProvider provider) throws IOException {

        gen.writeStartObject();
     
        gen.writeStringField("countyIsoCode", value.countyIsoCode());

        if (value.timeZone() != null) {
            gen.writeStringField("timeZone", value.timeZone());
        }

        gen.writeEndObject();
    }
}
