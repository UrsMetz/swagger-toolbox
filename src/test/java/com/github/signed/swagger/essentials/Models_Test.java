package com.github.signed.swagger.essentials;

import io.swagger.models.ArrayModel;
import io.swagger.models.properties.StringProperty;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Models_Test {

    private final Models models = new Models();
    private final ArrayModel arrayModel = new ArrayModel();

    @Test
    public void extract_definition_references_from_items_property() {
        arrayModel.setItems(new RefPropertyBuilder().withReferenceTo("referenced-model").build());

        assertThat(models.definitionReferencesIn(arrayModel).get(0).getSimpleRef(), is("referenced-model"));
    }

    @Test
    public void leave_none_ref_properties_alone() {
        arrayModel.setItems(new StringProperty());

        assertThat(models.definitionReferencesIn(arrayModel), Matchers.empty());
    }
}