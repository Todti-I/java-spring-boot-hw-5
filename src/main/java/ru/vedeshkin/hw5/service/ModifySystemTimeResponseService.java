package ru.vedeshkin.hw5.service;

import org.springframework.stereotype.Service;
import ru.vedeshkin.hw5.model.Request;
import ru.vedeshkin.hw5.model.Response;
import ru.vedeshkin.hw5.util.DateTimeUtil;

import java.util.Date;

@Service
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public void modify(Request request, Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
    }
}
