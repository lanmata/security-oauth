package com.umdc.security.jwt.json;

import io.jsonwebtoken.io.AbstractSerializer;
import io.jsonwebtoken.lang.Supplier;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.io.OutputStream;
import java.util.Objects;

/**
 * JJWT {@link io.jsonwebtoken.io.Serializer} backed by Jackson 3 ({@code tools.jackson}).
 * <p>
 * It replaces {@code io.jsonwebtoken.jackson.io.JacksonSerializer} (Jackson 2) so that the
 * library no longer depends on {@code com.fasterxml.jackson.core:jackson-databind}.
 * It is registered through {@code META-INF/services/io.jsonwebtoken.io.Serializer}.
 *
 * @param <T> the type of object to serialize
 * @since 0.0.4
 */
public class Jackson3JwtSerializer<T> extends AbstractSerializer<T> {

    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = newObjectMapper();

    private final ObjectMapper objectMapper;

    /**
     * Creates a serializer using the default Jackson 3 mapper.
     */
    public Jackson3JwtSerializer() {
        super();
        this.objectMapper = DEFAULT_OBJECT_MAPPER;
    }

    /**
     * Creates a serializer using the given Jackson 3 mapper.
     *
     * @param objectMapper the mapper to use, never {@code null}
     */
    public Jackson3JwtSerializer(ObjectMapper objectMapper) {
        super();
        this.objectMapper = Objects.requireNonNull(objectMapper, "ObjectMapper cannot be null.");
    }

    @SuppressWarnings("unchecked")
    static ObjectMapper newObjectMapper() {
        SimpleModule module = new SimpleModule("jjwt-jackson3");
        module.addSerializer((Class<Supplier<?>>) (Class<?>) Supplier.class, new SupplierSerializer());
        return JsonMapper.builder().addModule(module).build();
    }

    @Override
    protected void doSerialize(T t, OutputStream out) {
        objectMapper.writeValue(out, t);
    }

    private static final class SupplierSerializer extends ValueSerializer<Supplier<?>> {
        @Override
        public void serialize(Supplier<?> value, JsonGenerator gen, SerializationContext ctxt) {
            ctxt.writeValue(gen, value.get());
        }
    }
}
