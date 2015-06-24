package ru.koleslena.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.koleslena.demo.model.Item;
import ru.koleslena.demo.repository.ItemRepository;
import ru.koleslena.demo.service.ItemService;

/**
 * Created by elenko on 23.06.15.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Item getItemById(String id) {
        return itemRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Item> findByPublishedIsTrueOrderByName(Pageable pageable) {
        return itemRepository.findByPublishedIsTrueOrderByName(pageable);
    }

}
