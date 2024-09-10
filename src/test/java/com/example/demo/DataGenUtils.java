package com.example.demo;

import com.example.demo.entity.TopLevelEntityA;
import com.example.demo.entity.NestedEntityA;
import com.example.demo.entity.TopLevelEntityB;
import com.example.demo.entity.NestedEntityB;
import lombok.experimental.UtilityClass;
import org.instancio.Instancio;
import org.instancio.InstancioApi;
import org.instancio.Model;

import javax.persistence.ManyToOne;

import static com.example.demo.EntityModels.*;
import static org.instancio.Select.fields;
import static org.instancio.Select.types;


@UtilityClass
public class DataGenUtils {

    public static <T> InstancioApi<T> instancioApi(Class<T> type) {
        return customizeApiForPublicUsage(Instancio.of(type));
    }

    public static <T> InstancioApi<T> instancioApi(Model<T> model) {
        return customizeApiForPublicUsage(Instancio.of(model));
    }

    private static <T extends InstancioApi<?>> T customizeApiForPublicUsage(T api) {
        return (T) api.lenient()
                .setModel(types().of(TopLevelEntityA.class), TOP_LEVEL_ENTITY_A_MODEL)
                .setModel(types().of(NestedEntityA.class), NESTED_ENTITY_A_MODEL)
                .setModel(types().of(TopLevelEntityB.class), TOP_LEVEL_ENTITY_B_MODEL)
                .setModel(types().of(NestedEntityB.class), NESTED_ENTITY_B_MODEL)
                .ignore(fields().annotated(ManyToOne.class));
    }

}
