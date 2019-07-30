/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.sybit.machinesimulator.exception;

public class SybitException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String resourceName;
    private final String fieldNameValue;
    private final String originalMsg;
    
    public SybitException(String msg) {
    	super(msg);
    	this.resourceName = "";
    	this.fieldNameValue = "";
    	this.originalMsg = msg;
    }
	
    protected SybitException(String msg, String resourceName, String fieldNameValue, String originalMsg) {
        super(String.format("%s %s %s : '%s'", resourceName, msg, fieldNameValue, originalMsg));
        this.resourceName = resourceName;
        this.fieldNameValue = fieldNameValue;
        this.originalMsg = originalMsg;
    }
    
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldNameValue() {
        return fieldNameValue;
    }

    public String getOriginalMsg() {
        return originalMsg;
    }
    
}
