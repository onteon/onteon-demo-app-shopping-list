/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.interfaces.ShoppingListService;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.to.ShoppingListTO;

import java.nio.charset.StandardCharsets;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    @Override
    public byte[] generateTxt(@NonNull final ShoppingListTO shoppingList) {
        final StringBuilder sb = new StringBuilder();
        sb.append(shoppingList.getTitle())
                .append("\n")
                .append("-".repeat(shoppingList.getTitle().length()))
                .append("\n\n");
        shoppingList.getIngredients().forEach(ingredient -> sb.append("* ").append(ingredient).append("\n"));

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
