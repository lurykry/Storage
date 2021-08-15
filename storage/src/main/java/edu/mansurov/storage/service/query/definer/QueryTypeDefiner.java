package edu.mansurov.storage.service.query.definer;

import edu.mansurov.storage.domain.emums.QueryType;

public class QueryTypeDefiner {

    //TODO: написать логику определения типа запроса + кастомную ошибку
    public QueryType defineQueryType(String query) {
        if(query.contains("SELECT")) return QueryType.SELECT;
        if(query.contains("INSERT")) return QueryType.INSERT;
        if(query.contains("UPDATE")) return QueryType.UPDATE;
        if(query.contains("DELETE")) return QueryType.DELETE;

        throw new IllegalArgumentException("Unknown query type!");
    }
}
