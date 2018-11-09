package com.mbas.csdmassignment.resolvers;

import com.mbas.csdmassignment.entities.News;
import com.mbas.csdmassignment.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;


@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private final NewsRepository NewsRepository = null;

    public Iterable<News> news() {
        return NewsRepository.findAll();
    }

}