package ru.vedeshkin.hw5.service;

import ru.vedeshkin.hw5.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions position, double salary, double bonus, int workDays);
}
