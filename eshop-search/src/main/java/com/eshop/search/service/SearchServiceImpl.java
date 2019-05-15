package com.eshop.search.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.eshop.api.SearchService;
import com.eshop.entity.User;
import com.eshop.search.client.ESClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;


@Service(interfaceClass = SearchService.class)
@org.springframework.stereotype.Service
public class SearchServiceImpl implements SearchService {

    public String searchTest(){
        return "search11";
    }

    @Override
    public void indexUser(User user) {
        try {
            TransportClient client = ESClient.getClient();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(user);
            IndexResponse indexResponse = client.prepareIndex("eshop","user",String.valueOf(user.getId())).setSource(jsonStr).get();
            System.out.println(indexResponse.toString());
            ESClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delUser(Long id) {

    }

    @Override
    public User searchByKeyword(String key) {
        try {
            TransportClient client = ESClient.getClient();
            SearchResponse response = client.prepareSearch("eshop")
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.termQuery("multi", key))                 // Query
                    .get();
            response.getProfileResults();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
