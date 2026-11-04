package com.umdc.security.jwt.json;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Supplier;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

class Jackson3JwtJsonTest {

    /** Bean whose getter always fails, forcing a serialization error. */
    public static final class Failing {
        public String getBoom() {
            throw new IllegalStateException("boom");
        }
    }

    private static String json(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Test
    void serializesMapToJsonBytes() {
        Jackson3JwtSerializer<Object> serializer = new Jackson3JwtSerializer<>();
        assertEquals("{\"a\":\"b\"}", json(serializer.serialize(Map.of("a", "b"))));
    }

    @Test
    void serializesToOutputStream() {
        Jackson3JwtSerializer<Object> serializer = new Jackson3JwtSerializer<>();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serializer.serialize(List.of(1, 2), out);
        assertEquals("[1,2]", out.toString(StandardCharsets.UTF_8));
    }

    @Test
    void serializesJjwtSupplierUsingItsValue() {
        Jackson3JwtSerializer<Object> serializer = new Jackson3JwtSerializer<>();
        Supplier<String> supplier = () -> "value";
        assertEquals("{\"k\":\"value\"}", json(serializer.serialize(Map.of("k", supplier))));
    }

    @Test
    void serializerRejectsNullMapper() {
        assertThrows(NullPointerException.class, () -> new Jackson3JwtSerializer<>(null));
    }

    @Test
    void serializerWrapsFailuresInSerializationException() {
        Jackson3JwtSerializer<Object> serializer = new Jackson3JwtSerializer<>(JsonMapper.builder().build());
        assertThrows(SerializationException.class, () -> serializer.serialize(new Failing()));
    }

    @Test
    void deserializesJsonObjectIntoMap() {
        Jackson3JwtDeserializer<Object> deserializer = new Jackson3JwtDeserializer<>();
        Object result = deserializer.deserialize("{\"a\":1,\"b\":[true]}".getBytes(StandardCharsets.UTF_8));
        Map<?, ?> map = assertInstanceOf(Map.class, result);
        assertEquals(1, map.get("a"));
        assertEquals(List.of(true), map.get("b"));
    }

    @Test
    void deserializesFromReader() {
        Jackson3JwtDeserializer<Object> deserializer = new Jackson3JwtDeserializer<>();
        assertEquals(Map.of("x", "y"), deserializer.deserialize(new StringReader("{\"x\":\"y\"}")));
    }

    @Test
    void deserializerRejectsDuplicateKeys() {
        Jackson3JwtDeserializer<Object> deserializer = new Jackson3JwtDeserializer<>();
        byte[] dup = "{\"a\":1,\"a\":2}".getBytes(StandardCharsets.UTF_8);
        assertThrows(DeserializationException.class, () -> deserializer.deserialize(dup));
    }

    @Test
    void deserializerRejectsTrailingTokens() {
        Jackson3JwtDeserializer<Object> deserializer = new Jackson3JwtDeserializer<>();
        byte[] trailing = "{\"a\":1} {\"b\":2}".getBytes(StandardCharsets.UTF_8);
        assertThrows(DeserializationException.class, () -> deserializer.deserialize(trailing));
    }

    @Test
    void deserializerRejectsMalformedJson() {
        Jackson3JwtDeserializer<Object> deserializer = new Jackson3JwtDeserializer<>();
        byte[] bad = "{not-json".getBytes(StandardCharsets.UTF_8);
        assertThrows(DeserializationException.class, () -> deserializer.deserialize(bad));
    }

    @Test
    void deserializerRejectsNullArguments() {
        JsonMapper mapper = JsonMapper.builder().build();
        assertThrows(NullPointerException.class, () -> new Jackson3JwtDeserializer<>(null, Object.class));
        assertThrows(NullPointerException.class, () -> new Jackson3JwtDeserializer<>(mapper, null));
    }

    @Test
    void jjwtDiscoversJackson3ImplementationsThroughServiceLoader() {
        assertTrue(ServiceLoader.load(Serializer.class).stream()
                .anyMatch(p -> p.type().equals(Jackson3JwtSerializer.class)));
        assertTrue(ServiceLoader.load(Deserializer.class).stream()
                .anyMatch(p -> p.type().equals(Jackson3JwtDeserializer.class)));
    }

    @Test
    void jwtRoundTripWorksWithJackson3() {
        SecretKey key = Keys.hmacShaKeyFor("0123456789abcdef0123456789abcdef".getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder()
                .subject("user-1")
                .claim("type", "session")
                .claim("roles", List.of("A", "B"))
                .expiration(new Date(System.currentTimeMillis() + 60_000))
                .signWith(key)
                .compact();

        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        assertEquals("user-1", claims.getSubject());
        assertEquals("session", claims.get("type"));
        assertEquals(List.of("A", "B"), claims.get("roles"));
    }
}
