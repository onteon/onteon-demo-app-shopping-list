/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.printer.interfaces;

import lombok.NonNull;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.printer.exception.PrinterException;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
public interface PdfPrinter {
    void printTitle(@NonNull final String title) throws PrinterException;
    void printIngredient(@NonNull final String ingredient) throws PrinterException;
    byte[] close() throws PrinterException;
}
