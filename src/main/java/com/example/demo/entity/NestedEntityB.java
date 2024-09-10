package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@Accessors(chain = true)
public class NestedEntityB {

    private String attribute;

    @ManyToOne
    @ToString.Exclude
    private TopLevelEntityB topLevelEntity;

    @ToString.Exclude
    @Setter(PRIVATE)
    private Map<String, LeafEntityB> metadata = new HashMap<>();

}
