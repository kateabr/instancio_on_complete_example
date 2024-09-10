package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.ManyToOne;


@Getter
@Setter
@Accessors(chain = true)
public class LeafEntityB {

    private String key;

    private String attribute;

    @ManyToOne
    @ToString.Exclude
    private TopLevelEntityB event;

}
