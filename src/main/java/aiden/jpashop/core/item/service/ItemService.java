package aiden.jpashop.core.item.service;

import aiden.jpashop.core.item.domain.Item;
import aiden.jpashop.core.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ItemService {

    private final ItemRepository itemRepository;

    public Long createItem(Item item) {

        Item save = itemRepository.save(item);

        return save.getId();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not exist"));
    }

    public Page<Item> findAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
}
