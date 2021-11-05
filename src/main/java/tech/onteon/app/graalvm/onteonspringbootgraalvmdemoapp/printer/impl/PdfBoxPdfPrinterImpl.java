/*
 *  Copyright (c) 2021, Onteon Tech and/or its affiliates.
 *  All rights reserved.
 *  Use is subject to license terms.
 */
package tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.printer.impl;

import lombok.NonNull;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.printer.exception.PrinterException;
import tech.onteon.app.graalvm.onteonspringbootgraalvmdemoapp.printer.interfaces.PdfPrinter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Component
@Scope(scopeName = "prototype")
public class PdfBoxPdfPrinterImpl implements PdfPrinter {
    private final PDFont fontBold = PDType1Font.HELVETICA_BOLD;
    private final PDFont fontNormal = PDType1Font.HELVETICA;
    private final int lineSpace = 5;
    private final int topMargin = 25;
    private final int bottomMargin = 25;
    private final int leftMargin = 25;
    private final int titleFontSize = 26;
    private final int subTitleFontSize = 22;
    private final int titleBottomMargin = 20;
    private final int ingredientFontSize = 16;

    private PDDocument document;
    private PDPage page;
    private PDPageContentStream contentStream;
    private int topPosition;

    public PdfBoxPdfPrinterImpl() throws IOException {
        this.document = new PDDocument();
        this.page = new PDPage();
        this.contentStream = new PDPageContentStream(document, page);
        this.topPosition = 30;
    }

    @Override
    public void printTitle(@NonNull final String title) throws PrinterException {
        try {
            final String subTitle = "Shopping list";
            printCenteredText(fontBold, titleFontSize, title);
            printCenteredText(fontNormal, subTitleFontSize, subTitle);
            topPosition += titleBottomMargin;
        } catch (IOException e) {
            throw new PrinterException(String.format("Error during printing title, caused by: %s", e.getMessage()), e);
        }
    }

    @Override
    public void printIngredient(@NonNull final String ingredient) throws PrinterException {
        try {
            final float textHeight = getTextHeight(fontNormal, ingredientFontSize);
            final float squareSize = textHeight - 5;
            final float squareAdditionalTopMargin = 6;

            if (page.getMediaBox().getHeight() - bottomMargin <= topPosition + textHeight) {
                topPosition = topMargin;
                document.addPage(page);
                contentStream.close();
                this.page = new PDPage();
                this.contentStream = new PDPageContentStream(document, page);
            }

            drawCheckSquare(squareSize, squareAdditionalTopMargin);

            contentStream.beginText();
            contentStream.setFont(fontNormal, ingredientFontSize);
            contentStream.newLineAtOffset(leftMargin, page.getMediaBox().getHeight() - topPosition - textHeight);
            contentStream.showText("      " + ingredient);
            contentStream.endText();

            topPosition += textHeight;
            topPosition += lineSpace;
        } catch (IOException e) {
            throw new PrinterException(
                    String.format("Error during printing '%s' ingredient, caused by: %s", ingredient, e.getMessage()),
                    e
            );
        }
    }

    private void printCenteredText(
            @NonNull final PDFont font,
            @NonNull final int fontSize,
            @NonNull final String text
    ) throws IOException {
        final float textWidth = font.getStringWidth(text) / 1000 * fontSize;
        final float textHeight = getTextHeight(font, fontSize);

        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(
                (page.getMediaBox().getWidth() - textWidth) / 2,
                page.getMediaBox().getHeight() - topPosition - textHeight
        );
        contentStream.showText(text);
        contentStream.endText();

        topPosition += textHeight;
        topPosition += lineSpace;
    }

    private float getTextHeight(
            @NonNull final PDFont font,
            @NonNull final int fontSize
    ) {
        return font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
    }

    private void drawCheckSquare(final float size, final float additionalTopMargin) throws IOException {
        contentStream.addRect(
                leftMargin,
                page.getMediaBox().getHeight() - topPosition - (size + additionalTopMargin),
                size, size
        );
        contentStream.stroke();
    }

    @Override
    public byte[] close() throws PrinterException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            document.addPage(page);
            contentStream.close();
            document.save(byteArrayOutputStream);
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new PrinterException(String.format("Error during closing, caused by: %s", e.getMessage()), e);
        }
    }
}
