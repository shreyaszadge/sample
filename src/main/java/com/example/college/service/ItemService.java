package com.example.college.service;

import com.example.college.dto.ItemRequest;
import com.example.college.dto.ItemResponse;
import com.example.college.model.Item;
import com.example.college.repository.ItemRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemService {
    private static final Logger log = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemResponse> findAll() {
        var result = itemRepository.findAll();
        var response = result.stream().map(ItemResponse::from).collect(Collectors.toList());
        return response;
    }

    public ItemResponse findById(Long id) {
        var result = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        var response = ItemResponse.from(result);
        return response;
    }

    public ItemResponse create(ItemRequest request) {
        var entity = request.toEntity();
        var result = itemRepository.save(entity);
        var response = ItemResponse.from(result);
        return response;
    }

    public ItemResponse update(Long id, ItemRequest request) {
        var entity = request.toEntity();
        if (!(itemRepository.existsById(id))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        entity.setId(id);
        var result = itemRepository.save(entity);
        var response = ItemResponse.from(result);
        return response;
    }

    public void deleteById(Long id) {
        if (!(itemRepository.existsById(id))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        itemRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }
}
