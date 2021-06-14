package com.jacky.service;

import com.alibaba.fastjson.JSON;
import com.jacky.domain.Article;
import com.jacky.domain.ArticleRepository;
import com.jacky.domain.Topic;
import com.jacky.domain.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Transactional
    @Override
    public Topic findTopic(Long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        topic.getArticles();
        System.out.println(JSON.toJSONString(topic, true));
        return topic;
    }

    @Transactional
    @Override
    public Topic updateTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Transactional
    @Override
    public Topic includeArticle(Long id, Long articleId) {
        Topic topic = topicRepository.findById(id).orElse(null);
        Article article = articleRepository.findById(articleId).orElse(null);
        topic.getArticles().add(article);
        return topic;
    }

    @Transactional
    @Override
    public Topic unIncludeArticle(Long id, Long articleId) {
        Topic topic = topicRepository.findById(id).orElse(null);
        Article article = articleRepository.findById(articleId).orElse(null);
        topic.getArticles().remove(article);
        return topic;
    }

    @Transactional
    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
