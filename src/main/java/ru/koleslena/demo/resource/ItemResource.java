package ru.koleslena.demo.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import ru.koleslena.demo.model.Item;

/**
 * Created by elenko on 23.06.15.
 */
public class ItemResource extends Resource<Item> {

    public static final String LINK_NAME = "name";
    public static final String LINK_CONTENT = "content";

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ItemResource(Item content, Link... links) {
        super(content, links);
    }

}
