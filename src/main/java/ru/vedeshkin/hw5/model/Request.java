package ru.vedeshkin.hw5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /***
     * Уникальный идентификатор сообщение
     */
    @NotBlank
    @Size(max = 32)
    private String uid;

    /***
     * Уникальный идентификатор операции
     */
    @NotBlank
    @Size(max = 32)
    private String operationUid;

    /***
     * Имя системы отправителя
     */
    private Systems systemName;

    /***
     * Время создания сообщения
     */
    @NotBlank
    private String systemTime;

    /***
     * Наименование ресурса
     */
    private String source;

    /***
     * Позиция сотрудника
     */
    private Positions position;

    /***
     * Зарплата сотрудника
     */
    private Double salary;

    /***
     * Премия сотрудника
     */
    private Double bonus;

    /***
     * Количество рабочих дней
     */
    private Integer wordDays;

    /***
     * Уникальный идентификатор коммуникации
     */
    @Min(1)
    @Max(100_000)
    private Integer communicationId;

    /***
     * Уникальный идентификатор шаблона
     */
    private Integer templateId;

    /***
     * Код продукта
     */
    private Integer productCode;

    /***
     * Смс код
     */
    private Integer smsCode;


    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", snsCode=" + smsCode +
                '}';
    }
}
