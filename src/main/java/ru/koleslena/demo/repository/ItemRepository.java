package ru.koleslena.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.koleslena.demo.model.Item;

/**
 * Created by elenko on 23.06.15.
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, String> {


    Page<Item> findByPublishedIsTrueOrderByName(Pageable pageable);
}
