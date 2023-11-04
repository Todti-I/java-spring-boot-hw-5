package ru.vedeshkin.hw5.service;

import org.springframework.stereotype.Service;
import ru.vedeshkin.hw5.model.Request;
import ru.vedeshkin.hw5.util.DateTimeUtil;

import java.util.Date;

@Service
public class ModifySystemTimeRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
    }
}
