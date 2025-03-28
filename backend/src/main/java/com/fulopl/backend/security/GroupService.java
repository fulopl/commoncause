package com.fulopl.backend.security;

import com.fulopl.backend.model.entity.AppUser;
import com.fulopl.backend.model.entity.UserGroup;
import com.fulopl.backend.model.payload.GroupProperties;
import com.fulopl.backend.model.payload.GroupResponse;
import com.fulopl.backend.repository.GroupRepository;
import com.fulopl.backend.repository.UserRepository;
import com.fulopl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void checkIfOwnGroup(UserGroup group) {
        AppUser user = userService.getUser();
        if (!user.getId().equals(group.getOwner().getId()))
            throw new AuthorizationDeniedException("You have no write access to this object. ");
    }

    public void createGroup(GroupProperties body) {
        AppUser user = userService.getUser();
        UserGroup group = new UserGroup();
        group.setName(body.name());
        group.setOwner(user);
        groupRepository.save(group);
    }

    public void editGroup(Long id, GroupProperties body) {
        AppUser user = userService.getUser();
        UserGroup group = groupRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        if (!user.getId().equals(group.getOwner().getId()))
            throw new AuthorizationDeniedException("You have no write access to this object. ");

        group.setName(body.name());
        groupRepository.save(group);
    }

    public void addMember(Long groupId, Long memberId) {
        UserGroup group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No such group. "));
        checkIfOwnGroup(group);
        AppUser member = userRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("No such user. "));

        member.getGroups().add(group);
        userRepository.save(member);
    }

    @Transactional
    public void removeMember(Long groupId, Long memberId) {
        UserGroup group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No such group. "));
        checkIfOwnGroup(group);
        AppUser member = userRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("No such user. "));

        member.getGroups().remove(group);
        group.getMembers().remove(member);

        userRepository.save(member);
        groupRepository.save(group);
    }

    public Set<GroupResponse> getByOwner(AppUser owner) {
        Set<UserGroup> groups = groupRepository.findUserGroupsByOwner(owner);
        return groups.stream().map((userGroup) -> new GroupResponse(
                userGroup.getId(),
                userGroup.getName(),
                userGroup.getMembers().size()
        )).collect(Collectors.toSet());
    }

    public Set<GroupResponse> getByMember(AppUser member) {
        Set<UserGroup> groups = groupRepository.findUserGroupsByMembersContaining(member);
        return groups.stream().map((userGroup) -> new GroupResponse(
                userGroup.getId(),
                userGroup.getName(),
                userGroup.getMembers().size()
        )).collect(Collectors.toSet());
    }

    @Transactional
    public void deleteGroup(Long id) {
        UserGroup group = groupRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        checkIfOwnGroup(group);

        Set<Long> memberIdsToRemove = group.getMembers().stream().map(AppUser::getId).collect(Collectors.toSet());
        for (Long memberId : memberIdsToRemove) {
            removeMember(id, memberId);
        }
        groupRepository.deleteById(id);
    }
}
