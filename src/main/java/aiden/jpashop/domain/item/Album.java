package aiden.jpashop.domain.item;

import aiden.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("album")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Album extends Item {

    private String artist;

    private String etc;

    public Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}
