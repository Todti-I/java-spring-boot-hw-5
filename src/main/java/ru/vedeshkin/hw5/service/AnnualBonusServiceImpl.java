package ru.vedeshkin.hw5.service;

import org.springframework.stereotype.Service;
import ru.vedeshkin.hw5.model.Positions;
import ru.vedeshkin.hw5.util.DateTimeUtil;

import java.util.Date;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    private final boolean isLeapYear;

    public AnnualBonusServiceImpl() {
        this.isLeapYear = DateTimeUtil.isLeapYear(new Date());
    }

    public AnnualBonusServiceImpl(Date currentDate) {
        this.isLeapYear = DateTimeUtil.isLeapYear(currentDate);
    }

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays) {
        int daysInYear = isLeapYear ? 366 : 365;

        if (position.isManager()) {
            return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
        }

        return 0.0;
    }
}
