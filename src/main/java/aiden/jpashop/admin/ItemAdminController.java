package aiden.jpashop.admin;

import aiden.jpashop.core.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemAdminController {

    private final ItemService itemService;

    @PostMapping("/api/v1/admin/book")
    public ResponseEntity createItem(@RequestBody @Valid ItemAdminDto.CreateBook createBook) {

        Long bookId = itemService.createItem(createBook.toEntity());

        return ResponseEntity.ok(bookId);
    }
}
