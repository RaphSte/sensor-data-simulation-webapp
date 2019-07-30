/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.sybit.machinesimulator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends SybitException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9153386366344360716L;

    public ResourceNotFoundException(String resourceName, String fieldNameValue, String originalMsg) {
        super("not found with", resourceName, fieldNameValue, originalMsg);
    }
}