package ru.vedeshkin.hw5.service;

import org.springframework.stereotype.Service;
import ru.vedeshkin.hw5.model.Request;
import ru.vedeshkin.hw5.model.Response;

@Service
public class ModifyAnnulBonusResponseService implements ModifyResponseService {
    private final AnnualBonusService annualBonusService;

    public ModifyAnnulBonusResponseService(AnnualBonusService annualBonusService) {
        this.annualBonusService = annualBonusService;
    }

    @Override
    public void modify(Request request, Response response) {
        if (hasData(request)) {
            double annualBonus = annualBonusService.calculate(request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays());
            response.setAnnualBonus(annualBonus);
        }
    }

    private boolean hasData(Request request) {
        return request.getPosition() != null &&
                request.getSalary() != null &&
                request.getBonus() != null &&
                request.getWorkDays() != null;
    }
}
