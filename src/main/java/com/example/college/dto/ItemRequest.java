package com.example.college.dto;

import com.example.college.model.Item;
import jakarta.validation.constraints.*;
import java.util.*;

public class ItemRequest {

    private String name;

    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ItemRequest from(Item entity) {
        ItemRequest dto = new ItemRequest();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public Item toEntity() {
        Item entity = new Item();
        entity.setName(name);
        entity.setEmail(email);
        return entity;
    }
}
