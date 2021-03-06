package com.apray.blog.service;

import com.apray.blog.po.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SetService {

    Set saveSet(Set set);

    Set getSet(Long id);

    Page<Set> listSet(Pageable pageable);

    List<Set> listSet();

    List<Set> listSetTop(Integer size);

    Set updateSet(Long id, Set set);

    Set getSetByName(String name);

    void deleteSet(Long id);
}
