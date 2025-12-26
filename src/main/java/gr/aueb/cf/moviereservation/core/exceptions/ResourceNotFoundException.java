package gr.aueb.cf.moviereservation.core.exceptions;

public class ResourceNotFoundException extends AppException{

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(resource + " not found with " + field + " = " + value);
    }
}
