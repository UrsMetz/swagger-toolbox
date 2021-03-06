package com.github.signed.swagger.validate;

import java.io.PrintStream;
import java.util.List;

public class ValidationResult {

    private final List<ValidationMessage> messages;

    public ValidationResult(List<ValidationMessage> messages) {
        this.messages = messages;
    }

    public void writeTo(PrintStream printStream) {
        messages.forEach(message -> message.writeTo(printStream));
    }

    public boolean hasPassed() {
        return messages.isEmpty();
    }

    public boolean failed() {
        return !hasPassed();
    }
}
