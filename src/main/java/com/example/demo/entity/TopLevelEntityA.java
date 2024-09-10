package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@Accessors(chain = true)
public class TopLevelEntityA {

    @ToString.Exclude
    @Setter(PRIVATE)
    private Map<String, NestedEntityA> nestedEntities = new HashMap<>();

    @ToString.Exclude
    @Setter(PRIVATE)
    private List<LeafEntityA> leaves = new ArrayList<>();

    public TopLevelEntityA addLeaf(LeafEntityA data) {
        if (data != null) {
            leaves.add(data);
            data.setTopLevelEntity(this);
        }
        return this;
    }

}
