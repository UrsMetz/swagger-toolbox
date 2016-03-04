package com.github.signed.swagger.trim;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ToolboxPath_Test {

    @Test
    public void provide_the_source_format() {
        assertThat(new ToolboxPath("/").asString(), is("/"));
    }

    @Test
    public void reference_same_resource() {
        assertThat("they are referencing the same resource", referenceSameResource("/constant", "/constant"));
    }

    @Test
    public void different_url_template_variable_names_still_reference_the_same_resource() {
        assertThat("they are referencing the same resource", referenceSameResource("/{variable}", "/{different-name}"));
    }

    private boolean referenceSameResource(String path, String other) {
        return new ToolboxPath(path).referenceSameResource(new ToolboxPath(other));
    }

}