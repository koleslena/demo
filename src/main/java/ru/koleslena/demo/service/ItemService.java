package ru.koleslena.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.koleslena.demo.model.Item;

/**
 * Created by elenko on 23.06.15.
 */
public interface ItemService {
    Item getItemById(String id);

    Page<Item> findByPublishedIsTrueOrderByName(Pageable pageable);

}