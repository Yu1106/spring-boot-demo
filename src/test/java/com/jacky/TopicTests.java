package com.jacky;

import com.alibaba.fastjson.JSON;
import com.jacky.domain.Article;
import com.jacky.domain.Comment;
import com.jacky.domain.Topic;
import com.jacky.service.ArticleService;
import com.jacky.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TopicTests {

    @Autowired
    private TopicService topicService;

    @Test
    public void saveTopic() {
        Topic topic = new Topic();
        topic.setName("藝術");
        topicService.saveTopic(topic);
    }

    @Test
    public void updateTopic() {
        Topic topic = topicService.findTopic(30L);
        topic.setName("文學");
        topicService.saveTopic(topic);
    }

    @Test
    public void includeArticle() {
        topicService.includeArticle(30L, 20L);
    }

    @Test
    public void findTopic() {
        Topic topic = topicService.findTopic(31L);
    }

    @Test
    public void unIncludeArticle() {
        topicService.unIncludeArticle(30L, 20L);
    }

    @Test
    public void deleteTopic() {
        topicService.deleteTopic(30L);
    }
}
