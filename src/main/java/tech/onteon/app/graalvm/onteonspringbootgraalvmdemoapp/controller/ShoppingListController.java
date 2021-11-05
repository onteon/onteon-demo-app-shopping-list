/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.controller.pojo.request.ShoppingListRequest;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.converter.ShoppingListConverter;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.service.interfaces.ShoppingListService;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.service.to.ShoppingListTO;

import javax.validation.Valid;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@RestController
@RequestMapping("/api/v1/shopping-list")
public class ShoppingListController {
    private final ShoppingListConverter shoppingListConverter;
    private final ShoppingListService shoppingListService;

    public ShoppingListController(
            @Autowired final ShoppingListConverter shoppingListConverter,
            @Autowired final ShoppingListService shoppingListService
    ) {
        this.shoppingListConverter = shoppingListConverter;
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/generate/pdf")
    public ResponseEntity<byte[]> generatePdfShoppingList(final @RequestBody @Valid ShoppingListRequest request) {
        final ShoppingListTO shoppingListTO = shoppingListConverter.toShoppingListTO(request);
        final byte[] pdfBytes = shoppingListService.generatePdf(shoppingListTO);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String filename = "shopping-list.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
