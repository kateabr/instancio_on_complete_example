package com.example.demo;

import com.example.demo.entity.*;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.Collection;
import java.util.Map;

import static org.instancio.Assign.valueOf;
import static org.instancio.Select.field;
import static org.instancio.Select.root;


public class EntityModels {

    public static final Model<TopLevelEntityA> TOP_LEVEL_ENTITY_A_MODEL = Instancio.of(TopLevelEntityA.class)
            .ignore(field(TopLevelEntityA::getLeaves))
            .generate(field(TopLevelEntityA::getNestedEntities), gen -> gen.map().size(0))
            .onComplete(
                    field(TopLevelEntityA::getNestedEntities),
                    (Map<String, NestedEntityA> states) -> states
                            .forEach((key, value) -> value.setAttribute(key)
                                    .getLeaves()
                                    .values()
                                    .forEach(metadata -> metadata.setAttribute(key))))
            .onComplete(
                    root(),
                    (TopLevelEntityA event) -> event.getNestedEntities()
                            .values()
                            .stream()
                            .map(state -> state.setTopLevelEntity(event))
                            .map(NestedEntityA::getLeaves)
                            .map(Map::values)
                            .flatMap(Collection::stream)
                            .forEach(event::addLeaf))
            .toModel();

    public static final Model<NestedEntityA> NESTED_ENTITY_A_MODEL =
            Instancio.of(NestedEntityA.class)
                    .assign(valueOf(NestedEntityA::getAttribute)
                            .to(LeafEntityA::getAttribute))
                    .generate(field(NestedEntityA::getLeaves), gen -> gen.map().size(0))
                    .toModel();

    public static final Model<TopLevelEntityB> TOP_LEVEL_ENTITY_B_MODEL = Instancio.of(TopLevelEntityB.class)
            .ignore(field(TopLevelEntityB::getLeaves))
            .generate(field(TopLevelEntityB::getNestedEntities), gen -> gen.map().size(0))
            .onComplete(
                    field(TopLevelEntityB::getNestedEntities),
                    (Map<String, NestedEntityB> states) -> states
                            .forEach((key, value) -> value.setAttribute(key)
                                    .getMetadata()
                                    .values()
                                    .forEach(metadata -> metadata.setAttribute(key))))
            .onComplete(
                    root(),
                    (TopLevelEntityB event) -> event.getNestedEntities()
                            .values()
                            .stream()
                            .map(state -> state.setTopLevelEntity(event))
                            .map(NestedEntityB::getMetadata)
                            .map(Map::values)
                            .flatMap(Collection::stream)
                            .forEach(event::addLeaf))
            .toModel();

    public static final Model<NestedEntityB> NESTED_ENTITY_B_MODEL =
            Instancio.of(NestedEntityB.class)
                    .assign(valueOf(NestedEntityB::getAttribute)
                            .to(LeafEntityB::getAttribute))
                    .generate(field(NestedEntityB::getMetadata), gen -> gen.map().size(0))
                    .toModel();

}
