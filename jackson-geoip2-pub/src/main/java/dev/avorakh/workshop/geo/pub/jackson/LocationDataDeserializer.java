package dev.avorakh.workshop.geo.pub.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.avorakh.workshop.geo.pub.LocationData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class LocationDataDeserializer extends StdDeserializer<LocationData> {

    public LocationDataDeserializer() {

        this(null);
    }

    public LocationDataDeserializer(@Nullable Class<?> vc) {

        super(vc);
    }

    @Override
    public @NotNull LocationData deserialize(@NotNull JsonParser jp, @Nullable DeserializationContext ctxt) throws IOException, JacksonException {

        var jsonNode = (JsonNode) jp.getCodec().readTree(jp);

        var countyIsoCodeNode = jsonNode.get("countyIsoCode");
        var countyIsoCode = countyIsoCodeNode.asText();

        var timeZoneNode = jsonNode.get("timeZone");
        var timeZone = timeZoneNode == null ? null : timeZoneNode.asText();
        
        return LocationData.builder().countyIsoCode(countyIsoCode).timeZone(timeZone).build();
    }
}
