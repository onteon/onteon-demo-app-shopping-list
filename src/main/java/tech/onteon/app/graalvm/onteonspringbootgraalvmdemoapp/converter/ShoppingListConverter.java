/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.converter;

import org.springframework.stereotype.Component;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.controller.pojo.request.ShoppingListRequest;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.service.to.ShoppingListTO;

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
