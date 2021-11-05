/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.printer.exception;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
public class PrinterException extends RuntimeException {
    public PrinterException(String message, Throwable cause) {
        super(message, cause);
    }
}
