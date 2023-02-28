//
//
//
//
//package com.example.GateWay.security;
//
//import java.util.List;
//import java.util.function.Predicate;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RouterValidator {
//
//    public static final List<String> openApiEndpoints = List.of(
//            "/api/v1/authenticate",
//            "/api/v1/validate",
//            "/book/reader/1"
//    );
//
//    public Predicate<ServerHttpRequest> isSecured =
//            request -> openApiEndpoints
//                    .stream()
//                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
//
//}