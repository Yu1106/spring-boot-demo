package com.jacky.service;

import com.jacky.domain.Topic;

public interface TopicService {

    Topic saveTopic(Topic topic);

    Topic findTopic(Long id);

    Topic updateTopic(Topic topic);

    Topic includeArticle(Long id, Long articleId);

    Topic unIncludeArticle(Long id, Long articleId);

    void deleteTopic(Long id);
}
