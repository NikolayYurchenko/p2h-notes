package com.p2h.notes.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends Exception {

    private int code;

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    private String localizedKey;

    /**
     * Creates a localized description of this throwable.
     * Subclasses may override this method in order to produce a
     * locale-specific text.  For subclasses that do not override this
     * method, the default compile returns the same result as
     * {@code getMessage ( )}.
     *
     * @return The localized description of this throwable.
     * @since JDK1.1
     */
    @Override
    public String getLocalizedMessage() {

        return super.getLocalizedMessage();
    }
}
