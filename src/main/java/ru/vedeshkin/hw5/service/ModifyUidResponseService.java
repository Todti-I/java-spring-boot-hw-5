package ru.vedeshkin.hw5.service;

import org.springframework.stereotype.Service;
import ru.vedeshkin.hw5.model.Request;
import ru.vedeshkin.hw5.model.Response;

@Service
public class ModifyUidResponseService implements ModifyResponseService {
    @Override
    public void modify(Request request, Response response) {
        response.setUid(request.getUid());
    }
}
