package com.apray.blog.dao;

import com.apray.blog.po.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetRepository extends JpaRepository<Set, Long> {
    Set findByName(String name);

    @Query("select s from Set s")
    List<Set> findTop(Pageable pageable);
}
