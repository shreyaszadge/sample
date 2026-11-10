package com.example.college.controller;

import com.example.college.dto.ItemRequest;
import com.example.college.dto.ItemResponse;
import com.example.college.service.ItemService;
import jakarta.validation.Valid;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemResponse> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ItemResponse findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse create(@Valid @RequestBody ItemRequest request) {
        return itemService.create(request);
    }

    @PutMapping("/{id}")
    public ItemResponse update(@PathVariable Long id, @Valid @RequestBody ItemRequest request) {
        return itemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @GetMapping("/{id}/exists")
    public boolean existsById(@PathVariable Long id) {
        return itemService.existsById(id);
    }
}
