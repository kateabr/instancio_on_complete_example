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
public class TopLevelEntityB {

    @ToString.Exclude
    @Setter(PRIVATE)
    private Map<String, NestedEntityB> nestedEntities = new HashMap<>();

    @ToString.Exclude
    @Setter(PRIVATE)
    private List<LeafEntityB> leaves = new ArrayList<>();

    public TopLevelEntityB addLeaf(LeafEntityB tworkHitEventMetadata) {
        if (tworkHitEventMetadata != null) {
            this.leaves.add(tworkHitEventMetadata);
            tworkHitEventMetadata.setEvent(this);
        }
        return this;
    }

}
