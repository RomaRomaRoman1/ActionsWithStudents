package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import org.example.Action1;
/**
 * Hello world!
 *
 */
public class App 
{
    private static StudentCommandHandler studentCommandHandler = new StudentCommandHandler();
    public static void main( String[] args )
    {
        while(true) {//вызов бесконечного цикла
        printMessage();//вызов сообщения
        Command command = readCommand();//вызов метода считывания комманды
        if (command.getAction()==Action1.EXIT) {//если введенное значение соответствует действию выход - выход
            return;
        }
        else if (command.getAction()==Action1.ERROR) {//если введеное значение соответствует действию ошибка - выход из цикла
            continue;
            }
        else {
            studentCommandHandler.processCommand(command);//в остальных случаях - выполнение действий
        }
        }
    }
    private static Command readCommand(){
        Scanner scanner = new Scanner(System.in);//создаем сканер для считывания с консоли
        try {
            String code = scanner.nextLine();//первое считываемое значение кладем в переменную code
            Integer actionCode = Integer.valueOf(code);//преобразовываем считываемое значение в Integer
            Action1 action1 = Action1.fromCode(actionCode);//метод action для определения соответствия введеного Integer полю code action
            if (action1.isRequireAdditionalData()) {//если переменная требуются доп. данные true
                String data = scanner.nextLine();//считываем следующее значение из консоли в переменную data

                return new Command(action1, data);//возвращаем новый объект комманды с коммандой
            }
            else {
                return new Command(action1);//если переменная требуются доп. данные false, то создаем объект комманд передавая только action
            }
        }
        catch (Exception ex) {
            System.out.println("Проблема обработки ввода. " + ex.getMessage());//в случае ошибки выводим сообщение с ошибкой
            return new Command(Action1.ERROR);//и комманду с ошибкой
        }
    }
    private static void printMessage() {
        System.out.println("_________________________");
        System.out.println("0. выход");
        System.out.println("1. Создание данных");
        System.out.println("2. Обновление данных");
        System.out.println("3. Удаление данных");
        System.out.println("4. Вывод статистика по курсам");
        System.out.println("5. Вывод статистика по Городам");
        System.out.println("6. Поиск по фамилии");
        System.out.println("_________________________");

    }
}
