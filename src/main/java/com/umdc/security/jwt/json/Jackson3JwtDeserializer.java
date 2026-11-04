package com.umdc.security.jwt.json;

import io.jsonwebtoken.io.AbstractDeserializer;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.Reader;
import java.util.Objects;

/**
 * JJWT {@link io.jsonwebtoken.io.Deserializer} backed by Jackson 3 ({@code tools.jackson}).
 * <p>
 * It replaces {@code io.jsonwebtoken.jackson.io.JacksonDeserializer} (Jackson 2). Duplicate JSON keys and
 * trailing tokens are rejected, which matches the strict behaviour of the original JJWT implementation.
 * It is registered through {@code META-INF/services/io.jsonwebtoken.io.Deserializer}.
 *
 * @param <T> the type of object to deserialize
 * @since 0.0.4
 */
public class Jackson3JwtDeserializer<T> extends AbstractDeserializer<T> {

    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = newObjectMapper();

    private final ObjectMapper objectMapper;
    private final Class<T> returnType;

    /**
     * Creates a deserializer that reads JSON into a generic {@link Object} (maps, lists and scalars).
     */
    @SuppressWarnings("unchecked")
    public Jackson3JwtDeserializer() {
        super();
        this.objectMapper = DEFAULT_OBJECT_MAPPER;
        this.returnType = (Class<T>) Object.class;
    }

    /**
     * Creates a deserializer using the given mapper and target type.
     *
     * @param objectMapper the mapper to use, never {@code null}
     * @param returnType   the type to deserialize into, never {@code null}
     */
    public Jackson3JwtDeserializer(ObjectMapper objectMapper, Class<T> returnType) {
        super();
        this.objectMapper = Objects.requireNonNull(objectMapper, "ObjectMapper cannot be null.");
        this.returnType = Objects.requireNonNull(returnType, "Return type cannot be null.");
    }

    static ObjectMapper newObjectMapper() {
        return JsonMapper.builder()
                .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
                .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
                .build();
    }

    @Override
    protected T doDeserialize(Reader reader) {
        return objectMapper.readValue(reader, returnType);
    }
}
