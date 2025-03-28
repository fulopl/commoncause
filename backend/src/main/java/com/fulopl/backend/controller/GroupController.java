package com.fulopl.backend.controller;

import com.fulopl.backend.model.entity.AppUser;
import com.fulopl.backend.model.payload.GroupProperties;
import com.fulopl.backend.model.payload.GroupResponse;
import com.fulopl.backend.repository.GroupRepository;
import com.fulopl.backend.security.GroupService;
import com.fulopl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupRepository groupRepository;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public GroupController(GroupRepository groupRepository, GroupService groupService, UserService userService) {
        this.groupRepository = groupRepository;
        this.groupService = groupService;
        this.userService = userService;
    }

    @PostMapping()
    public void createGroup(@RequestBody GroupProperties body) {
        groupService.createGroup(body);
    }

    @PutMapping("/{id}")
    public void editGroup(@PathVariable Long id, @RequestBody GroupProperties body) {
        groupService.editGroup(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @PatchMapping("/add-member")
    public void addMember(@RequestParam(name = "group") Long groupId
            , @RequestParam(name = "member") Long memberId) {
        groupService.addMember(groupId, memberId);
    }

    @PatchMapping("/remove-member")
    public void removeMember(@RequestParam(name = "group") Long groupId
            , @RequestParam(name = "member") Long memberId) {
        groupService.removeMember(groupId, memberId);
    }

    @GetMapping("/owner")
    public Set<GroupResponse> getAsOwner() {
        AppUser owner = userService.getUser();
        return groupService.getByOwner(owner);
    }

    @GetMapping("/member")
    public Set<GroupResponse> getAsMember() {
        AppUser member = userService.getUser();
        return groupService.getByMember(member);
    }
}
