package com.mbas.newsfeeddist.resolvers;

import com.mbas.newsfeeddist.entities.Feed;
import com.mbas.newsfeeddist.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;


@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private final FeedRepository FeedRepository = null;

    public Iterable<Feed> feed() {
        return FeedRepository.findAll();
    }

}