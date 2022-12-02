package br.com.devdojo.awesome.devdojospringboot.error;

public class ValidationErrorDetails extends ErrorDetails{
    private String field;
    private String fieldMessage;

    //getter e setter

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    //construtor
    public ValidationErrorDetails() {
    }

    //BUILDER

    public static final class ValidationErrorDetailsBuilder {
        private String title;
        private int status;
        private String details;
        private long timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private ValidationErrorDetailsBuilder() {
        }

        public static ValidationErrorDetailsBuilder newBuilder() {
            return new ValidationErrorDetailsBuilder();
        }

        public ValidationErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ValidationErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ValidationErrorDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ValidationErrorDetailsBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ValidationErrorDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ValidationErrorDetailsBuilder field(String field) {
            this.field = field;
            return this;
        }

        public ValidationErrorDetailsBuilder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setTitle(title);
            validationErrorDetails.setStatus(status);
            validationErrorDetails.setDetails(details);
            validationErrorDetails.setTimestamp(timestamp);
            validationErrorDetails.setDeveloperMessage(developerMessage);
            validationErrorDetails.field = field;
            validationErrorDetails.fieldMessage = fieldMessage;
            return validationErrorDetails;
        }
    }
}
