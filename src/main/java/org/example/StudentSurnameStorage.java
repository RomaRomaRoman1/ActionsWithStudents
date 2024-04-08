package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StudentSurnameStorage {
    private TreeMap<String, Set<Long>> surnamesTreeMap = new TreeMap<String, Set<Long>>();
    public Set<Long> getStudentBySurnamesLessOrEqualThen(String surnameOne, String surnameTwo) {
        if (surnameOne.compareTo(surnameTwo)<=0) {//проверка первое значение стоит впереди по алфавиту или позади
            return surnamesTreeMap.entrySet().stream()
                    .filter(entry -> (entry.getKey().compareTo(surnameTwo)) <= 0 && (entry.getKey().compareTo(surnameOne)) >= 0)
                    .flatMap(entry -> entry.getValue().stream())
                    .collect(Collectors.toSet());//берем диапазон значений с помощью фильтрации, от первой фамилии до 2
        } else {
                return surnamesTreeMap.entrySet().stream()
                    .filter(entry->(entry.getKey().compareTo(surnameOne))<=0 && (entry.getKey().compareTo(surnameTwo))>=0)
                    .flatMap(entry->entry.getValue().stream())
                    .collect(Collectors.toSet());//берем диапазон значений с помощью фильтрации, от второй фамилии до 1
            }

    }
    public Set<Long> getStudentBySurnameLessOrEqualThen(String surname) {
        return surnamesTreeMap.get(surname).
                stream().
                collect(Collectors.toSet());
    }
    public void studentCreated(Long id, String surname) {
        Set<Long> existingIds = surnamesTreeMap.getOrDefault(surname, new HashSet<>());//получаем set по ключу фамилии из TreeMap или создаем новый HashSet
        existingIds.add(id);//добавляем в Set id адрес, полученный из StudentStorage
        surnamesTreeMap.put(surname, existingIds);//кладем в TreeMap ключ фамилию и значение ID
    }
    public void studentDeleted (Long id, String surname) {
        surnamesTreeMap.get(surname).remove(id);
    }
    public void studentUpdated(Long id, String oldSurname, String newSurname) {
        studentDeleted(id, oldSurname);
        studentCreated(id, newSurname);
    }

}
