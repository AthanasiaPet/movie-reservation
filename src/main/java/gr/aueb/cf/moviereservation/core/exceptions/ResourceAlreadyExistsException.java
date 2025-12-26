package gr.aueb.cf.moviereservation.core.exceptions;

public class ResourceAlreadyExistsException extends AppException {

    public ResourceAlreadyExistsException(String resource, String field, Object value) {
        super(resource + " already exists with " + field + " = " + value);
    }
}
