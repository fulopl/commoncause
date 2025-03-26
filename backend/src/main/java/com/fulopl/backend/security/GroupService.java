package com.fulopl.backend.security;

import com.fulopl.backend.model.entity.AppUser;
import com.fulopl.backend.model.entity.UserGroup;
import com.fulopl.backend.repository.GroupRepository;
import com.fulopl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserService userService;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public void createGroup(String name) {
        AppUser user = userService.getUser();
        UserGroup group = new UserGroup();
        group.setName(name);
        group.setOwner(user);
        groupRepository.save(group);
    }
}
