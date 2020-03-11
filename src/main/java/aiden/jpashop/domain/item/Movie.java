package aiden.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie {

    private String director;

    private String actor;

    public Movie(String director, String actor) {
        this.director = director;
        this.actor = actor;
    }
}
