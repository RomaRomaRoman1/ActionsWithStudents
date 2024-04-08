package org.example;

import java.util.Map;

public class StudentCommandHandler {
    private StudentStorage studentStorage = new StudentStorage();
    public void processCommand(Command command) {
        Action1 action1 = command.getAction();
        switch (action1) {
            case CREATE -> {
                processCreateCommand(command);
                break;
            }
            case UPDATE -> {
                processUpdateCommand(command);
            }
            case DELETE -> {
                processDeleteCommand(command);
            }
            case STATS_BY_COURSE -> {
                processStatsByCourseCommand(command);
                break;
            }
            case SEARCH -> {
                processSearchCommand(command);
            }
            case STATS_BY_CITY -> {
                processStatsByCityCommand();
            }
            default -> {
                System.out.println("Действие " + action1 + " не поддерживается");
            }
        }

        System.out.println("Обработка команды. Действие: " + command.getAction().name() + ", data: " + command.getData());
    }

    private void processStatsByCityCommand() {
        Map<String, Long> data = studentStorage.getCountByCity();
        studentStorage.printMap(data);
    }

    private void processSearchCommand (Command command) {
        String regexTwoSurnames = "^[a-zA-Zа-яА-Я]+,[a-zA-Zа-яА-Я]+$";
        String regexAllLetters = "^[a-zA-Z]+$";
        String surname = command.getData();
        if (isNumeric(surname)) {//проверка на отсутствия числовых данных в фамилии
            System.out.println("Фамилия не должна быть числом!!!");
            return;
        } else if (surname.matches(regexTwoSurnames)||surname.equals("")||surname.matches(regexAllLetters)) {//проверка на соответствие допустимых форматов входных данных
            studentStorage.search(surname);
        }
        else System.out.println("Фамилия должна содержать только буквы, пустое значение- все студенты, две Фамилии - диапазон значений от одной фамилии до другой в алф. порядке");
    }
    private void processStatsByCourseCommand(Command command) {
        Map<String, Long> data = studentStorage.getCountByCourse();
        studentStorage.printMap(data);
    }

    public void processCreateCommand (Command command) {
        String data = command.getData();
        String [] dataArray = data.split(",");
        if (isNumeric(dataArray[0]) || isNumeric(dataArray[1])||isNumeric(dataArray[2])||isNumeric(dataArray[3])) {//проверка на отсутствия числовых данных в имени, фамилии, городе, курсе
            System.out.println("Имя, Фамилия, Курс и город не должны быть числом!!!");
            return;
        }
        checkNumber(dataArray[4]);//проверка возраста на положительное число и не дробное
        Student student = new Student();
        student.setSurName(dataArray[0]);
        student.setName(dataArray[1]);
        student.setCourse(dataArray[2]);
        student.setCity(dataArray[3]);
        student.setAge(Integer.valueOf(dataArray[4]));
        studentStorage.createStudent(student);
        studentStorage.printAll();
    }

    public void processUpdateCommand (Command command) {
        String data = command.getData();
        String [] dataArray = data.split(",");
        checkNumber(dataArray[0]);//проверка id на положительное число и не дробное
        Long id = Long.valueOf(dataArray[0]);
        Student student = new Student();
        student.setSurName(dataArray[1]);
        student.setName(dataArray[2]);
        student.setCourse(dataArray[3]);
        student.setCity(dataArray[4]);
        student.setAge(Integer.valueOf(dataArray[5]));
        studentStorage.updateStudent(id, student);
        studentStorage.printAll();
    }
    public void processDeleteCommand (Command command) {
        String data = command.getData();
        checkNumber(data);//проверка id на положительное число и не дробное
        Long id = Long.valueOf(data);
        studentStorage.deleteStudent(id);
        studentStorage.printAll();
    }

    //Дополнительные методы для проверки:
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    private void checkNumber (String number) {
        try {
            if (Integer.parseInt(number)<0)
            {
                System.out.println("Возраст должен быть положительным целым числом!");
                return;
            };//проверка id на целое число
        }
        catch (NumberFormatException e) {
            System.out.println("Возраст должен быть целым числом!");
            return;
        }
    }
}
