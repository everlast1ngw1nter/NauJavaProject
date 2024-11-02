package ru.everlast1ngw1nter.NauJava.dto;

import java.util.List;
import ru.everlast1ngw1nter.NauJava.models.Product;

public record ProductsReportGenerationData(List<Product> products, long generationTimeMillis) {
}
