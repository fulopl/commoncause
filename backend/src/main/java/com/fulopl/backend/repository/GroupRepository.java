package com.fulopl.backend.repository;

import com.fulopl.backend.model.entity.AppUser;
import com.fulopl.backend.model.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<UserGroup, Long> {
    Set<UserGroup> findUserGroupsByOwner(AppUser owner);

    Set<UserGroup> findUserGroupsByMembersContaining(AppUser member);
}
