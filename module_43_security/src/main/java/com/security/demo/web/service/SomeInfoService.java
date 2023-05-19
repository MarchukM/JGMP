package com.security.demo.web.service;

import com.security.demo.web.model.SomeInfo;
import com.security.demo.web.repo.SomeInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SomeInfoService {

    @Autowired
    private SomeInfoRepo repo;

    public Collection<SomeInfo> findAll() {
        return Streamable.of(repo.findAll()).toList();
    }
}
