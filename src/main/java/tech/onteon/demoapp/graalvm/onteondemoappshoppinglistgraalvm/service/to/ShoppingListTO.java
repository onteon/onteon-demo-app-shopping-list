/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.demoapp.graalvm.onteondemoappshoppinglistgraalvm.service.to;

import lombok.Data;

import java.util.List;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Data
public class ShoppingListTO {
    private final String title;
    private final List<String> ingredients;
}
