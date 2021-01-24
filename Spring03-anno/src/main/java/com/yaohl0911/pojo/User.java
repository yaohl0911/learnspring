package com.yaohl0911.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("Singleton")
@Data
public class User {
    @Value("yaohl")
    private String name;
}
