package com.example.demo;

import com.example.demo.entity.NestedEntityA;
import com.example.demo.entity.TopLevelEntityA;
import com.example.demo.entity.TopLevelEntityB;
import com.example.demo.entity.NestedEntityB;
import org.instancio.Model;
import org.junit.jupiter.api.Test;

import static com.example.demo.DataGenUtils.instancioApi;
import static org.assertj.core.api.Assertions.*;
import static org.instancio.Select.field;


class OnCompleteTest {

    private static final Model<TopLevelEntityA> TOP_LEVEL_ENTITY_A_MODEL_WITH_DESCENDANTS =
            instancioApi(TopLevelEntityA.class)
                    .generate(field(TopLevelEntityA::getNestedEntities), gen -> gen.map().size(1))
                    .generate(field(NestedEntityA::getLeaves), gen -> gen.map().size(1))
                    .toModel();
    private static final Model<TopLevelEntityB> TOP_LEVEL_ENTITY_B_MODEL_WITH_DESCENDANTS =
            instancioApi(TopLevelEntityB.class)
                    .generate(field(TopLevelEntityB::getNestedEntities), gen -> gen.map().size(1))
                    .generate(field(NestedEntityB::getMetadata), gen -> gen.map().size(1))
                    .toModel();

    @Test
    void onCompleteTest() {
        // has 1 element in the leaves field
        assertWith(
                instancioApi(TOP_LEVEL_ENTITY_A_MODEL_WITH_DESCENDANTS).create(),
                entity -> assertThat(entity.getLeaves()).hasSize(1));

        // should also have 1 element in the leaves field, but has none
        assertWith(
                instancioApi(TOP_LEVEL_ENTITY_B_MODEL_WITH_DESCENDANTS).create(),
                entity -> assertThat(entity.getLeaves()).isEmpty());
    }

}
