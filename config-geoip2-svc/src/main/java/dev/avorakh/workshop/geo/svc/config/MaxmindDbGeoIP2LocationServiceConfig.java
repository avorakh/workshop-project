package dev.avorakh.workshop.geo.svc.config;

import com.maxmind.db.NoCache;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseProvider;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;

@Slf4j
public class MaxmindDbGeoIP2LocationServiceConfig {

    @Bean
    public @NotNull DatabaseProvider maxmindDBProvider(@NotNull Environment env) throws IOException {

        var databaseFile = Path.of(env.getRequiredProperty("maxmind.db.city.file.path"));

        if (!Files.exists(databaseFile) || !Files.isRegularFile(databaseFile)) {
            log.error("Maxmind DB file does not exist or the DB path is invalid - '{}'", databaseFile);

            throw new InvalidParameterException("Maxmind DB file does not exist or the DB path is invalid.");
        }

        return new DatabaseReader
            .Builder(databaseFile.toFile())
            .fileMode(Reader.FileMode.MEMORY_MAPPED)
            .withCache(NoCache.getInstance())
            .build();
    }
}
