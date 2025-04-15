package com.colvir.homework3_singletonWithPrototype.impl;

import com.colvir.homework3_singletonWithPrototype.PrototypeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class PrototypeServiceImpl implements PrototypeService {

    private String innerStr;

    public void setInnerStr(String innerStr) {
        this.innerStr = innerStr;
    }

    @Override
    public String DoSmth2(String s) {
        return "DoSmth " + innerStr + s;
    }
}
