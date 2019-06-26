package com.mbas.newsfeeddist.repositories;

import com.mbas.newsfeeddist.entities.Feed;
import org.springframework.data.repository.CrudRepository;

public interface FeedRepository extends CrudRepository<Feed, Long> {}
