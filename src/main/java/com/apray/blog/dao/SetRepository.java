package com.apray.blog.dao;

import com.apray.blog.po.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<Set, Long> {
    Set findByName(String name);
}
