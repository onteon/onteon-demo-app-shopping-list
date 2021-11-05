/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.converter;

import org.springframework.stereotype.Component;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.controller.pojo.request.ShoppingListRequest;
import tech.onteon.demoapp.graalvm.onteondemoappspringbootgraalvm.service.to.ShoppingListTO;

import java.util.Optional;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Component
public class ShoppingListConverter {
    public ShoppingListTO toShoppingListTO(final ShoppingListRequest shoppingListRequest) {
        return Optional.ofNullable(shoppingListRequest)
                .map(v -> new ShoppingListTO(
                        v.getTitle(),
                        v.getIngredients()
                ))
                .orElse(null);
    }
}
