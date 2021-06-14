package com.jacky.service;

import com.jacky.domain.Comment;

public interface CommentService {

    Comment saveComment(Comment comment);

    void deleteComment(Long id);
}
