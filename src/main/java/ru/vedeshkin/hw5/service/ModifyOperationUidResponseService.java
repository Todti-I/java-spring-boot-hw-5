package ru.vedeshkin.hw5.service;

import org.springframework.stereotype.Service;
import ru.vedeshkin.hw5.model.Request;
import ru.vedeshkin.hw5.model.Response;

import java.util.UUID;

@Service
public class ModifyOperationUidResponseService implements ModifyResponseService {
    @Override
    public void modify(Request request, Response response) {
        response.setOperationUid(request.getOperationUid());
    }
}
