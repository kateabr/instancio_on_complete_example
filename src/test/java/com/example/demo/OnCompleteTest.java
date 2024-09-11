package com.example.demo;

import com.example.demo.entity.EntityA;
import com.example.demo.entity.EntityB;
import org.instancio.Instancio;
import org.instancio.InstancioApi;
import org.instancio.Model;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;
import static org.instancio.Select.*;


class OnCompleteTest {

    public static final Model<EntityA> ENTITY_A_MODEL = Instancio.of(EntityA.class)
            .ignore(field(EntityA::getProperty))
            .onComplete(root(), (EntityA entity) -> entity.setProperty("something"))
            .toModel();

    public static final Model<EntityB> ENTITY_B_MODEL = Instancio.of(EntityB.class)
            .ignore(field(EntityB::getProperty))
            .onComplete(root(), (EntityB entity) -> entity.setProperty("something"))
            .toModel();

    private static <T> InstancioApi<T> instancioApi(Class<T> type) {
        return Instancio.of(type)
                .lenient()
                .setModel(types().of(EntityA.class), ENTITY_A_MODEL)
                .setModel(types().of(EntityB.class), ENTITY_B_MODEL);
    }

    @Test
    void onCompleteTest() {
        // property initialized in the onComplete callback
        assertWith(
                instancioApi(EntityA.class).create(),
                entity -> assertThat(entity.getProperty()).isNotEmpty());

        // property not initialized: onComplete callback was not invoked
        assertWith(
                instancioApi(EntityB.class).create(),
                entity -> assertThat(entity.getProperty()).isNull());
    }

}
