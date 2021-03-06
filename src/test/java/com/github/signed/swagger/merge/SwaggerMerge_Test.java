package com.github.signed.swagger.merge;

import com.github.signed.swagger.essentials.SwaggerBuilder;
import com.github.signed.swagger.essentials.SwaggerMother;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class SwaggerMerge_Test {

    private final SwaggerBuilder first = SwaggerMother.emptyApiDefinition();
    private final SwaggerBuilder second = SwaggerMother.emptyApiDefinition();
    private final SwaggerMerge swaggerMerge = new SwaggerMerge();

    @Before
    public void setUp() {
        first.withPath("/{variable}/constant").withPost();
        second.withPath("/{argument}/constant").withOption();
    }

    @Test
    public void same_path_but_different_operations_results_in_merge_conflict() {
        assertThat("merging the same path with different operations should not be successful", !mergeResult().success());
    }

//    @Test
//    public void report_conflicting_path() throws Exception {
//        assertThat(mergeResult().successOr(), is("/{variable|argument}/constant"));
//    }

    private MergeResult mergeResult() {
        return swaggerMerge.merge(first.build(), second.build());
    }

}