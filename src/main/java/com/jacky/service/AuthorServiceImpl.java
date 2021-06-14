package com.jacky.service;

import com.jacky.domain.Author;
import com.jacky.domain.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    @Override
    public Author updateAuthor() {
        Author author = new Author();
        author.setPhone("29698408");
        author.setNickName("余建志");
        author.setSignDate(new Date());
        Author author1 = authorRepository.save(author);

        author1.setPhone("123");
        Author author2 = authorRepository.save(author);

        return author2;
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }



    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
