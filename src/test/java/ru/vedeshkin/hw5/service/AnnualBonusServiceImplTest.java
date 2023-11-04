package ru.vedeshkin.hw5.service;

import org.junit.jupiter.api.Test;
import ru.vedeshkin.hw5.model.Positions;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Positions position = Positions.MANAGER;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl(calendar.getTime());
        double result = service.calculate(position, salary, bonus, workDays);

        double expected = 841152.2633744855;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateWithLeapYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, 1);
        Positions position = Positions.MANAGER;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl(calendar.getTime());
        double result = service.calculate(position, salary, bonus, workDays);

        double expected = 843456.7901234567;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateNotForManager() {
        Positions position = Positions.DEV;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl();
        double result = service.calculate(position, salary, bonus, workDays);

        double expected = 0.0;
        assertThat(result).isEqualTo(expected);
    }
}