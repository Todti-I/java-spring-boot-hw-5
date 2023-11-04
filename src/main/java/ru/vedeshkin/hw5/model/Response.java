package ru.vedeshkin.hw5.model;

import lombok.Builder;
import lombok.Data;
import ru.vedeshkin.hw5.util.DateTimeUtil;

import java.util.Date;

@Data
@Builder
public class Response {

    private String uid;
    private String operationUid;
    private String systemTime;
    private Codes code;
    private Double annualBonus;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;

    public static Response createDefault(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", code=" + code +
                ", errorCode=" + errorCode +
                ", errorMessage=" + errorMessage +
                '}';
    }
}
