package com.github.signed.swagger.modify;

import com.github.signed.swagger.essentials.SwaggerBuilder;
import io.swagger.models.Operation;
import org.junit.Test;

import java.util.function.Consumer;

import static com.github.signed.swagger.essentials.PathMother.anyPath;
import static com.github.signed.swagger.essentials.SwaggerMother.emptyApiDefinition;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SwaggerModify_Test {

    private final SwaggerBuilder swaggerBuilder = emptyApiDefinition();
    private final SwaggerModify swaggerModify = new SwaggerModify();
    private final String path = anyPath();
    private final OperationIdentifier identifier = OperationIdentifier.Get(path);
    private final Consumer<Operation> modification = operation -> operation.setOperationId("someMethodName");

    @Test
    public void report_if_path_does_does_not_exist() {
        ModificationResult result = modify();

        assertThat("should not be successful, path does not exist", !result.success());
    }

    @Test
    public void apply_modification_if_operation_is_present() {
        swaggerBuilder.withPath(path).withGet();

        ModificationResult result = modify();

        assertThat("should be successful", result.success());
        assertThat(result.swagger().getPath(path).getGet().getOperationId(), is("someMethodName"));
    }

    private ModificationResult modify() {
        return swaggerModify.modify(swaggerBuilder.build(), identifier, modification);
    }

}
