package ru.vedeshkin.hw5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.vedeshkin.hw5.exception.ValidationFailedException;
import ru.vedeshkin.hw5.model.*;
import ru.vedeshkin.hw5.service.ModifyRequestService;
import ru.vedeshkin.hw5.service.ModifyResponseService;
import ru.vedeshkin.hw5.service.ValidationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final List<ModifyRequestService> modifyRequestServices;
    private final List<ModifyResponseService> modifyResponseServices;


    @Autowired
    public MyController(ValidationService validationService,
                        List<ModifyRequestService> modifyRequestServices,
                        List<ModifyResponseService> modifyResponseServices) {
        this.validationService = validationService;
        this.modifyRequestServices = modifyRequestServices;
        this.modifyResponseServices = modifyResponseServices;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        Response response = Response.createDefault();
        log.info("request: {}", request);
        log.info("response: {}", response);

        if (isUnsupportedUid(request.getUid())) {
            return handleUnsupportedCodeException(response);
        }
        if (hasValidationErrors(bindingResult)) {
            return handleValidationException(response);
        }

        modifyRequestServices.forEach(service -> service.modify(request));
        sendFeedbackToService2(request);
        modifyResponseServices.forEach(service -> service.modify(request, response));
        log.info("response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean isUnsupportedUid(String uid) {
        boolean isUnsupported = Objects.equals(uid, "123");
        if (isUnsupported) {
            log.warn("error: Uid = {} unsupported", uid);
        }
        return isUnsupported;
    }

    private boolean hasValidationErrors(BindingResult bindingResult) {
        try {
            validationService.isValid(bindingResult);
            return false;
        } catch (ValidationFailedException e) {
            log.error("error: {}", e.getMessage());
            return true;
        }
    }

    private ResponseEntity<Response> handleUnsupportedCodeException(Response response) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNSUPPORTED);
        log.info("response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Response> handleValidationException(Response response) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
        response.setErrorMessage(ErrorMessages.VALIDATION);
        log.info("response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private void sendFeedbackToService2(Request request) {
        try {
            HttpEntity<Request> httpEntity = new HttpEntity<>(request);
            new RestTemplate().exchange("http://localhost:8084/feedback",
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    });
        } catch (RestClientException e) {
            log.error("service 2 not available");
        }
    }
}
