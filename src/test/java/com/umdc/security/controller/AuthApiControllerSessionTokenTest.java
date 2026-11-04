package com.umdc.security.controller;

import com.umdc.security.service.AuthService;
import com.umdc.security.to.AuthRequest;
import com.umdc.security.to.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthApiControllerSessionTokenTest {

    @Test
    void generateTokenSessionDelegatesToService() {
        AuthService service = mock(AuthService.class);
        AuthRequest request = new AuthRequest("alias", "pwd");
        when(service.token(request, "session")).thenReturn(ResponseEntity.ok(new AuthResponse("jwt")));

        ResponseEntity<AuthResponse> response = new AuthApiController(service).generateTokenSession("session", request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jwt", response.getBody().token());
    }
}
