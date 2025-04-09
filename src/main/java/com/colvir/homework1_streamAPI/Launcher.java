package com.colvir.homework1_streamAPI;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Launcher {

    public static void main(String[] args){
        System.out.println("Enter text:");
        Scanner console = new Scanner(System.in);
        String text = console.nextLine();

        Arrays.stream(text.split(" "))
                .map(e -> e.replaceAll("[^A-Za-zА-Яа-я0-9]", "").toLowerCase())
                .collect(Collectors.toMap(key->key,value->1, Integer::sum))
                .entrySet().stream()
                .sorted((e1, e2) -> {
                    return e1.getValue().compareTo(e2.getValue()) * -1;
                })
                .forEach(e -> System.out.println(e.getKey()));
     }

}
