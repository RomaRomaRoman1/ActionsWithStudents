package org.example;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentStorage {
    private Map <Long, Student> studentStorageMap = new HashMap<>();//создаем карту с id и экземпляром Student
    private StudentSurnameStorage studentSurnameStorage = new StudentSurnameStorage();//создаем экземпляр где хранится TreeMap
    private Long currentId= 0L;//начальное значение id

    public Long createStudent (Student student) {//Метод создания студента
    Long nextId = getNextId();//получение id адреса
    studentStorageMap.put(nextId, student);//кладем в HashMap значение id и экземпляр student
    studentSurnameStorage.studentCreated(nextId, student.getSurName());//передаем в метод создания TreeMap id и фамилии студента
    return nextId;//методу возвращаем сгенерированный id
    }
    public boolean updateStudent (Long id, Student student) {
    if (!studentStorageMap.containsKey(id)) {
        return false;
    }else {
        studentSurnameStorage.studentUpdated(id, studentStorageMap.get(id).getSurName(), student.getSurName());
        studentStorageMap.put(id, student);
        return true;
    }

    }
    public boolean deleteStudent (Long id) {
        Student remove = studentStorageMap.remove(id);
        if (remove!=null) {
            studentSurnameStorage.studentDeleted(id, remove.getSurName());
        }

        return remove!=null;
    }
    public void search (String surname){
        Set<Long> students;
        String [] data = surname.split(",");
        if (surname.equals("")) {
            printAll();
            return;
        }
         else if (data.length==1) {
            students = studentSurnameStorage.getStudentBySurnameLessOrEqualThen(surname);
            students.forEach(student -> System.out.println(studentStorageMap.get(student)));
            return;
        }
        else {
            String surnameOne = data[0];
            String surnameTwo = data[1];
            students = studentSurnameStorage.getStudentBySurnamesLessOrEqualThen(surnameOne, surnameTwo);
            students.forEach(student-> System.out.println(studentStorageMap.get(student)));
            return;
        }
    }



    public Long getNextId() {//метод получения id с помощью счетчика
        currentId = currentId +1;
        return currentId;
    }
    public void printAll() {
        System.out.println(studentStorageMap);
    }
    public void printMap(Map<String, Long> data) {
        data.entrySet().stream().forEach(e->{
            System.out.println(e.getKey() + " - " + e.getValue());
        } );
    }
    public Map<String, Long> getCountByCourse() {
//        return studentStorageMap.values().stream().
//                collect(Collectors.toMap(s->s.getCourse(), s->1L, (s1, s2) -> s1+s2));
        return studentStorageMap.values().stream().collect(Collectors.groupingBy(Student::getCourse, Collectors.counting()));
    }
    public Map<String, Long> getCountByCity() {
//        return studentStorageMap.values().stream().
//                collect(Collectors.toMap(s->s.getCity(), s->1L, (s1, s2) -> s1+s2));
        return studentStorageMap.values().stream().collect(Collectors.groupingBy(Student::getCity, Collectors.counting()));
    }
}
