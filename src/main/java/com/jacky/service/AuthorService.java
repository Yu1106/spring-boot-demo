package com.jacky.service;

import com.jacky.domain.Author;

public interface AuthorService {

    Author updateAuthor();

    Author saveAuthor(Author author);

    Author updateAuthor(Author author);

    Author findAuthor(Long id);

    void deleteAuthor(Long id);
}
