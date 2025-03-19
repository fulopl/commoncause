package com.fulopl.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @PostMapping("/create")
    public void createGroup(@RequestBody String name, Long ownerId) {

    }

    @PutMapping("/{id}")
    public void editGroup(@PathVariable Long id, @RequestBody String name, Long ownerId) {

    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {

    }

    @PatchMapping("/add/{id}")
    public void addMember(@PathVariable Long id) {

    }

    @PatchMapping("/remove/{id}")
    public void removeMember(@PathVariable Long id) {

    }
}
