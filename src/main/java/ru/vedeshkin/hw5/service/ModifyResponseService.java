package ru.vedeshkin.hw5.service;

import ru.vedeshkin.hw5.model.Request;
import ru.vedeshkin.hw5.model.Response;

public interface ModifyResponseService {
    void modify(Request request, Response response);
}
