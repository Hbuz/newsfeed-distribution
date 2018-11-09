package com.mbas.csdmassignment.repositories;

import com.mbas.csdmassignment.entities.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {}
