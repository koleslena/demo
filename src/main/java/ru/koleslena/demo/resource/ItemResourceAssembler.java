package ru.koleslena.demo.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import ru.koleslena.demo.controller.ItemController;
import ru.koleslena.demo.exception.ResourceNotFoundException;
import ru.koleslena.demo.model.Item;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by elenko on 23.06.15.
 */
@Component
public class ItemResourceAssembler implements ResourceAssembler<Item, ItemResource> {
    @Override
    public ItemResource toResource(Item entity) {
        ItemResource resource = new ItemResource(entity);
        resource.setCount(1);
        try {
            resource.add(linkTo(methodOn(ItemController.class).getItemById(entity.getId()))
                    .withSelfRel());

            Link nameLink = linkTo(methodOn(ItemController.class)
                    .addItem(entity.getId()))
                    .withRel(ItemResource.LINK_NAME);
            resource.add(nameLink);

/*            resource.add(linkTo(methodOn(WebsiteRestController.class).getUserProfile(entity.getAuthorId()))
                    .withRel(ItemResource.LINK_NAME_AUTHOR));*/
        } catch (ResourceNotFoundException ex) {
            //do nothing
        }
        return resource;
    }


}
