package krupkoillia.chesstracker.gateway.filter;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public final class UserIdFilterFunctions {

    private static final String USER_ID_HEADER_NAME = "X-User-Id";

    private UserIdFilterFunctions() {}

    public static HandlerFilterFunction<ServerResponse, ServerResponse> addUserId() {
        return (request, next) -> {
            Authentication authentication = SecurityContextHolder
                    .getContext()
                    .getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()
                    || !(authentication instanceof JwtAuthenticationToken jwtAuthentication)) {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
            }

            String userId = jwtAuthentication.getToken().getSubject();

            if (userId == null || userId.isBlank()) {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
            }

            ServerRequest modifiedRequest = ServerRequest.from(request)
                .headers(headers -> headers.set(USER_ID_HEADER_NAME, userId))
                .build();

            return next.handle(modifiedRequest);
        };
    }

}
