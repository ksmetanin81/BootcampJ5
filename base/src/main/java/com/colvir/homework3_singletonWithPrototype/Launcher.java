package com.colvir.homework3_singletonWithPrototype;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
            SingletonService singletonService = applicationContext.getBean(SingletonService.class);
            singletonService.setInnerStr("InnerStr ");
            System.out.println(singletonService.DoSmth1("Test1"));
            singletonService.setInnerStr("InnerStr2 ");
            System.out.println(singletonService.DoSmth1("Test2"));
        }
    }
}
