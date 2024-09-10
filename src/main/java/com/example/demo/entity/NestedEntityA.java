package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@Accessors(chain = true)
public class NestedEntityA {

    private String attribute;

    @ManyToOne
    @ToString.Exclude
    private TopLevelEntityA topLevelEntity;

    @ToString.Exclude
    @Setter(PRIVATE)
    private Map<String, LeafEntityA> leaves = new HashMap<>();

}
