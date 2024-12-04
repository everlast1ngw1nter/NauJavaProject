package ru.everlast1ngw1nter.NauJava;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.everlast1ngw1nter.NauJava.domain.Counter;

@Component
public class CommandProcessor
{
    private final Counter counter;

    @Autowired
    public CommandProcessor(Counter counter)
    {
        this.counter = counter;
    }
    public void processCommand(String input)
    {
        String[] cmd = input.split(" ");
        switch (cmd[0])
        {
            case "create" ->
            {
                counter.addProduct((cmd[1]), Integer.parseInt(cmd[2]));
                System.out.println("Продукт добавлен в каталог");
            }
            case "add_consumed_today" ->
            {
                counter.addTodayConsumedProduct((cmd[1]));
                System.out.println("Съеденный продукт добавлен");
            }
            case "add_consumed" ->
            {
                counter.addConsumedProduct((cmd[1]), LocalDate.parse(cmd[2]));
                System.out.println("Съеденный продукт добавлен");
            }
            case "delete" ->
            {
                counter.deleteProductByName((cmd[1]));
                System.out.println("Продукт удален из каталога");
            }
            case "get_calories" ->
            {
                var calories = counter.getCaloriesByProduct((cmd[1]));
                System.out.println("У продукта " + calories + " калорий");
            }
            case "get_calories_by_date" ->
            {
                var calories = counter.getCaloriesByDate(LocalDate.parse(cmd[1]));
                System.out.println("В эту дату вы потребили " + calories + " калорий");
            }
            case "get_calories_by_interval" ->
            {
                var calories = counter.getCaloriesByInterval(LocalDate.parse(cmd[1]), LocalDate.parse(cmd[2]));
                System.out.println("В этот промежуток времени вы потребили " + calories + " калорий");
            }
            default -> System.out.println("Введена неизвестная команда");
        }
    }
}
