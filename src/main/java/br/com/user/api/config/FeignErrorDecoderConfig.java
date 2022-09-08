package br.com.user.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.isNull;
import static java.util.Optional.of;

@Slf4j
@RequiredArgsConstructor
public class FeignErrorDecoderConfig implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus statusCode = HttpStatus.valueOf(response.status());
        if (response.status() >= 400 && response.status() <= 499) {
            if (response.status() == 401) {
                return new HttpClientErrorException(statusCode, "Unauthorized");
            }
            return of(response)
                    .map(resp -> new HttpClientErrorException(statusCode, getBodyAsString(response)))
                    .orElse(new HttpClientErrorException(statusCode));
        }else if(response.status() >= 500 && response.status() <= 599){
            return of(response)
                    .map(resp -> new HttpServerErrorException(statusCode, getBodyAsString(response)))
                    .orElse(new HttpServerErrorException(statusCode));
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    private String getBodyAsString(Response response) {
        if (isNull(response.body())) {
            return "Body is null";
        }
        try {
            return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8.displayName());
        } catch (IOException e) {
            log.error("Feign error decoder exception: ", e);
        }
        return "Message not found";
    }
}