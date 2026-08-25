package com.storeflow.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorDto(
LocalDateTime timestamp,
int status,
String  error,
String message,
String path,
Map<String,String> errors

) {
}
