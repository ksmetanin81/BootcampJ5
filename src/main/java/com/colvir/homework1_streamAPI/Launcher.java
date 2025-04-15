package com.colvir.homework1_streamAPI;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Enter text:");
        Scanner console = new Scanner(System.in);
        String text = console.nextLine();
        if (!text.isBlank()) {
            Arrays.stream(text.split(" "))
                    .map(e -> e.replaceAll("[^A-Za-zА-Яа-я0-9]", "").toLowerCase())
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(Map.Entry::getKey)
                    .forEach(System.out::println);
        } else {
            System.out.println("the text is empty");
        }
    }

}
