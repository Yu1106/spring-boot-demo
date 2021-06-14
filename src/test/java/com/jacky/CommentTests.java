package com.jacky;

import com.jacky.domain.Article;
import com.jacky.domain.Comment;
import com.jacky.service.ArticleService;
import com.jacky.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Test
    public void saveCommentTest(){

        Article article = articleService.findArticle(20L);

        Comment comment = new Comment();
        comment.setContent("測試文章內容");
        comment.setArticle(article);

        commentService.saveComment(comment);
    }

    @Test
    public void deleteCommentTest(){
        commentService.deleteComment(29L);
    }
}
