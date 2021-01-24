package com.yaohl0911.diy;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

public class DiyPointCut {
    public void diyBefore() {
        System.out.println("diy before");
    }

    public void diyAfter() {
        System.out.println("diy after");
    }
}
