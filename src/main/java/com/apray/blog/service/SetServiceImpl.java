package com.apray.blog.service;

import com.apray.blog.NotFoundException;
import com.apray.blog.dao.SetRepository;
import com.apray.blog.po.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SetServiceImpl implements SetService{

    @Autowired
    private SetRepository setRepository;

    @Transactional
    @Override
    public Set saveSet(Set set) {
        return setRepository.save(set);
    }

    @Transactional
    @Override
    public Set getSet(Long id) {
        return setRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Set> listSet(Pageable pageable) {
        return setRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Set updateSet(Long id, Set set) {
        Set s = setRepository.getOne(id);
        if (s == null) {
            throw new NotFoundException("文集不存在。");
        }
        BeanUtils.copyProperties(set, s);
        return setRepository.save(s);
    }

    @Override
    public List<Set> listSetTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return setRepository.findTop(pageable);
    }

    @Override
    public Set getSetByName(String name) {
        return setRepository.findByName(name);
    }

    @Override
    public List<Set> listSet() {
        return setRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteSet(Long id) {
        setRepository.deleteById(id);
    }
}
