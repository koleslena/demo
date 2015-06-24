package ru.koleslena.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.koleslena.demo.exception.ResourceNotFoundException;
import ru.koleslena.demo.model.Item;
import ru.koleslena.demo.resource.ItemResource;
import ru.koleslena.demo.resource.ItemResourceAssembler;
import ru.koleslena.demo.service.ItemService;
import ru.koleslena.demo.util.UtilConstants;


/**
 * Created by elenko on 23.06.15.
 */
@Controller
@RequestMapping( value = "/api", produces = "application/json" )/*hal+*/
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemResourceAssembler itemResourceAssembler;

    /**
     * Returns public items with pagination.
     *
     * @param pageable
     * @param assembler
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/public/items")
    public HttpEntity<PagedResources<ItemResource>> getItems(
            @PageableDefault(size = 10/*UtilConstants.DEFAULT_RETURN_RECORD_COUNT*/, page = 0) Pageable pageable,
            PagedResourcesAssembler<Item> assembler) {

        Page<Item> items = itemService.findByPublishedIsTrueOrderByName(pageable);
        return new ResponseEntity<>(assembler.toResource(items, itemResourceAssembler), HttpStatus.OK);
    }

    /**
     * Returns public item by id.
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/public/items/{itemId}")
    public HttpEntity<ItemResource> getItemById(@PathVariable("itemId") String id)
            throws ResourceNotFoundException {

        Item item = itemService.getItemById(id);

        if(item == null)
            throw new ResourceNotFoundException("Resource Not Found");

        ItemResource resource = itemResourceAssembler.toResource(item);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/public/items/add/{itemId}")
    public HttpEntity<ItemResource> addItem(@PathVariable("itemId") String id) {
        return null;
    }
}
