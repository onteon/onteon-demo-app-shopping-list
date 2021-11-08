/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappshoppinglistgraalvm.service.interfaces;

import lombok.NonNull;
import tech.onteon.demoapp.graalvm.onteondemoappshoppinglistgraalvm.service.to.ShoppingListTO;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
public interface ShoppingListService {
    byte[] generateTxt(@NonNull final ShoppingListTO shoppingList);
}
