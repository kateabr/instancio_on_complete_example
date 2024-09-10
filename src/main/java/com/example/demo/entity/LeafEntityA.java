package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;


@Getter
@Setter
@Accessors(chain = true)
public class LeafEntityA {

    private String key;

    private String attribute;

    @ManyToOne
    @ToString.Exclude
    private TopLevelEntityA topLevelEntity;

}
