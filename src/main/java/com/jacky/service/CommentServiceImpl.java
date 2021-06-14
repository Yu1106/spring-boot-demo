package com.jacky.service;

import com.jacky.domain.Comment;
import com.jacky.domain.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long id) {
        // 移除關係才能刪除
        Comment comment = commentRepository.findById(id).orElse(new Comment());
        // 等同於下面移除關係
        comment.clearComment();
        // 清除關係才能刪除
//        List<Comment> comments = comment.getArticle().getComments();
//        for (Comment comment1 : comments) {
//            if (id == comment1.getId()) {
//                comments.remove(comment1);
//                break;
//            }
//        }
        commentRepository.deleteById(id);
//        commentRepository.deleteBy(id);
    }
}
