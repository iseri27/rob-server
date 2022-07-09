package com.xjtu.dbc.robserver.level;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Level {
    private int value;
    private String name;
    private String[] names = {"", "小木槌", "碎星锤", "博浪锤", "夜陨", "昊天锤", "大摆锤"};

    public Level(int value) {
        this.value = value;
        this.name = names[value];
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
