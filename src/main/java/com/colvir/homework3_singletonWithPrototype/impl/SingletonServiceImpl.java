package com.colvir.homework3_singletonWithPrototype.impl;

import com.colvir.homework3_singletonWithPrototype.PrototypeService;
import com.colvir.homework3_singletonWithPrototype.SingletonService;
import org.springframework.stereotype.Service;

@Service
public class SingletonServiceImpl implements SingletonService {

    private final PrototypeService prototypeService;

    public SingletonServiceImpl(PrototypeService prototypeService) {
        this.prototypeService = prototypeService;
    }

    @Override
    public void setInnerStr(String innerStr) {
        prototypeService.setInnerStr(innerStr);
    }

    @Override
    public String DoSmth1(String s) {
        return prototypeService.DoSmth2(s);
    }
}
