package com.example;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search")
public class SearchEndpoint {

    @Inject
    QueryEmitter queryEmitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> hello(@QueryParam("query") String query,
                              @QueryParam("userId") String userId) {

        if (query == null) {
            return Collections.emptyList();
        }
        queryEmitter.send(String.format("%s|%s", userId, query));

        List<String> keywords = Arrays.stream(query.split("[^\\w]+"))
              .collect(Collectors.toList());

        PanacheQuery<PageKeyword> dbQuery = PageKeyword.find("keyword in ?1", keywords);
        return dbQuery
              .stream()
              .map(pk -> pk.page)
              .distinct()
              .collect(Collectors.toList());
    }
}