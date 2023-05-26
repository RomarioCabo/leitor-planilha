package br.com.planilha.leitorapp.infrastructure.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Configuration
public class JacksonConfig {

  @Bean("defaultObjectMapper")
  @Primary
  public ObjectMapper defaultObjectMapper() {
    return buildObjectMapper(LOWER_CAMEL_CASE);
  }

  private ObjectMapper buildObjectMapper(PropertyNamingStrategy strategy) {
    return new Jackson2ObjectMapperBuilder()
        .propertyNamingStrategy(strategy)
        .featuresToDisable(FAIL_ON_UNKNOWN_PROPERTIES)
        .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
        .featuresToDisable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
        .modules(new JavaTimeModule())
        .build();
  }
}
