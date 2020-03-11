package aiden.jpashop.core.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item {

    private String author;

    private String isbn;

    public Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }

}
