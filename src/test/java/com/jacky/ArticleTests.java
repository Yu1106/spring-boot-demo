package com.jacky;

import com.alibaba.fastjson.JSON;
import com.jacky.domain.*;
import com.jacky.service.ArticleService;
import com.jacky.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ArticleTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void saveArticle() {
        Article article = new Article();
        article.setTitle("標題");
        article.setContent("內容");

        Comment comment1 = new Comment("評論內容");
        Comment comment2 = new Comment("評論內容2");

        article.addComment(comment1);
        article.addComment(comment2);

        articleService.saveArticle(article);
    }

    @Test
    public void updateArticle() {
        Article article = articleService.findArticle(14L);
        article.setContent("測試修改");

        articleService.updateArticle(article);
    }

    @Test
    public void findArticle() {
        Article article = articleService.findArticle(20L);
        System.out.println(JSON.toJSONString(article, true));
    }

    @Test
    public void deleteArticle() {
        articleService.deleteArticle(23L);
    }
}
