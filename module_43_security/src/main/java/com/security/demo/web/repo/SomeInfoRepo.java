package com.security.demo.web.repo;

import com.security.demo.web.model.SomeInfo;
import org.springframework.data.repository.CrudRepository;

public interface SomeInfoRepo extends CrudRepository<SomeInfo, Long> {
}
