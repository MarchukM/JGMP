package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Sport {
    @Id
    private Long id;
    private String name;

    public Sport(String name) {
        this.name = name;
    }
}