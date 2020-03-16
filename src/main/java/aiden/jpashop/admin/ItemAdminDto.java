package aiden.jpashop.admin;

import aiden.jpashop.core.item.domain.Book;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Data
public class ItemAdminDto {

    @Data
    @NoArgsConstructor
    public static class CreateBook{

        @NotNull
        private String name;

        @NumberFormat
        private int price;

        @NumberFormat
        private int stockQuantity;

        @NotNull
        private String author;

        @NotNull
        private String isbn;

        @Builder
        public CreateBook(String name, int price, int stockQuantity, String author, String isbn) {
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.author = author;
            this.isbn = isbn;
        }

        public Book toEntity() {
            return Book.builder()
                    .name(this.name)
                    .author(this.author)
                    .price(this.price)
                    .stockQuantity(this.stockQuantity)
                    .build();

        }
    }
}
