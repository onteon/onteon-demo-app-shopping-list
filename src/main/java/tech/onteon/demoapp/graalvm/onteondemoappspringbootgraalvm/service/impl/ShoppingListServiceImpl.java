/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.printer.interfaces.PdfPrinter;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.interfaces.ShoppingListService;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.to.ShoppingListTO;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    private final ApplicationContext applicationContext;

    public ShoppingListServiceImpl(
            @Autowired final ApplicationContext applicationContext
    ) {
        this.applicationContext = applicationContext;
    }

    @Override
    public byte[] generatePdf(@NonNull final ShoppingListTO shoppingList) {
        final PdfPrinter pdfPrinter = applicationContext.getBean(PdfPrinter.class);
        pdfPrinter.printTitle(shoppingList.getTitle());
        shoppingList.getIngredients().forEach(pdfPrinter::printIngredient);
        return pdfPrinter.close();
    }
}
