/*Java: знакомство и как пользоваться базовым API (семинары)
Урок 5. Хранение и обработка данных ч2: множество коллекций Map

Задание:
Реализуйте структуру телефонной книги с помощью HashMap.
Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, как одного человека с разными телефонами.
Вывод должен быть отсортирован по убыванию числа телефонов.

Обучающийся: ШИТОВ Олег Владимирович, "Разработчик Python", поток 4544, будни, утро.  30.06.2023
 */

package org.example;

import java.util.*;

public class Task_5 {
    public static void main(String[] args) {
        // Создание и инициализация телефонной книги
        Map<String, List<String>> phoneBook = new HashMap<>();

        // Добавление записей в телефонную книгу
        addPhoneNumber(phoneBook, "Иванов", "1111111111");
        addPhoneNumber(phoneBook, "Петров", "2222222222");
        addPhoneNumber(phoneBook, "Сидоров", "3333333333");
        addPhoneNumber(phoneBook, "Иванов", "4444444444");
        addPhoneNumber(phoneBook, "Петров", "5555555555");
        addPhoneNumber(phoneBook, "Иванов", "6666666666");

        // Вывод телефонной книги в порядке убывания числа телефонов по именам
        printPhoneBook(phoneBook);
    }

    // Метод для добавления номера телефона в телефонную книгу
    public static void addPhoneNumber(Map<String, List<String>> phoneBook, String name, String phoneNumber) {
        // Если имя уже есть в телефонной книге, то добавляем номер к существующему
        // списку, иначе создаем новый список
        if (phoneBook.containsKey(name)) {
            List<String> numbers = phoneBook.get(name);
            numbers.add(phoneNumber);
            phoneBook.put(name, numbers);
        } else {
            List<String> numbers = new ArrayList<>();
            numbers.add(phoneNumber);
            phoneBook.put(name, numbers);
        }
    }

    // Метод для вывода телефонной книги в порядке убывания числа телефонов по
    // именам
    public static void printPhoneBook(Map<String, List<String>> phoneBook) {
        // Создаем список записей с использованием Set для убывания числа телефонов
        Set<Map.Entry<String, List<String>>> entries = new TreeSet<>((entry1, entry2) -> {
            int sizeCompare = Integer.compare(entry2.getValue().size(), entry1.getValue().size());
            // Если число телефонов одинаково, то сортируем по имени
            return sizeCompare != 0 ? sizeCompare : entry1.getKey().compareTo(entry2.getKey());
        });

        // Добавляем все записи из телефонной книги в список
        entries.addAll(phoneBook.entrySet());

        // Выводим имена и телефоны в порядке убывания числа телефонов
        for (Map.Entry<String, List<String>> entry : entries) {
            String name = entry.getKey();
            List<String> numbers = entry.getValue();
            System.out.println(name);
            for (String number : numbers) {
                System.out.println(number);
            }
        }
    }
}
/*
 * Пример применения:
 * Иванов
 * 1111111111
 * 4444444444
 * 6666666666
 * Петров
 * 2222222222
 * 5555555555
 * Сидоров
 * 3333333333
 */