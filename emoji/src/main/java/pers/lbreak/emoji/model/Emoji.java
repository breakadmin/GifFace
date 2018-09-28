package pers.lbreak.emoji.model;

import lombok.Data;
/**
 * Created by break on 2018/9/13/013.
 */
@Data
public class Emoji {

    String name;
    int res;

    public Emoji(String name, int res) {
        this.name = name;
        this.res = res;
    }
}
