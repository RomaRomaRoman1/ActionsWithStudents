package org.example;

import java.util.Objects;
import java.util.stream.Stream;

//Variant, which user chose
public enum Action1 {
    EXIT(0,false),
    CREATE(1, true),
    UPDATE(2, true),
    DELETE(3, true),
    STATS_BY_COURSE(4,false),
    STATS_BY_CITY(5,false),
    SEARCH(6, true),
    ERROR(-1, false);
    private Integer code;
    private boolean requireAdditionalData;

    public Integer getCode() {//возвращает поле перечисления code
        return code;
    }

    public boolean isRequireAdditionalData() {//возвращает поле перечисления isRequireAdditionalData
        return requireAdditionalData;
    }

    Action1(Integer code, boolean requireAdditionalData) {//конструктор на два параметра
        this.code = code;
        this.requireAdditionalData = requireAdditionalData;
    }
    public static Action1 fromCode (Integer code) {//метод получения значения из Integer
        return Stream.of(Action1.values()).//создаем поток из значений всех элементов
                filter(action -> Objects.
                        equals(action.getCode(), code)).//фильтруем элементы соответствующие полученному в методе коду
                findFirst().//получаем первое значение
                orElseGet(() -> {//если значение не получено, то вывести ошибку
            System.out.println("Неизвестный код действия" + code);
            return Action1.ERROR;//возвращает код ошибки
        });
    }
}
