/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.printer.interfaces;

import lombok.NonNull;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.printer.exception.PrinterException;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
public interface PdfPrinter {
    void printTitle(@NonNull final String title) throws PrinterException;
    void printIngredient(@NonNull final String ingredient) throws PrinterException;
    byte[] close() throws PrinterException;
}
