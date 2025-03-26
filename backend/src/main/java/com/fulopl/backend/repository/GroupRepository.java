package com.fulopl.backend.repository;

import com.fulopl.backend.model.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<UserGroup, Long> {
}
