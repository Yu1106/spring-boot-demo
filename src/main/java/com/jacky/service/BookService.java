package com.jacky.service;

import com.jacky.domain.Book;
import com.jacky.domain.BookRepository;
import com.jacky.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 查詢所有的書單列表
     *
     * @return
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 分頁查詢書單列表
     *
     * @return
     */
    public Page<Book> findAllByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * 提交一個書單信息
     *
     * @param book
     * @return
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 獲取一條表單訊息
     *
     * @param id
     * @return
     */
    public Book findOne(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(new Book());
    }

    /**
     * 刪除一條訊息
     *
     * @param id
     */
    public void deleteOne(long id) {
        bookRepository.deleteById(id);
    }

    /**
     * 根據author查詢一個書單列表
     *
     * @param author
     * @return
     */
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * 根據 author and status 查詢一個書單列表
     *
     * @param author
     * @param status
     * @return
     */
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    /**
     * @param des
     * @return
     */
    public List<Book> findByDescription(String des) {
//        return bookRepository.findByDescriptionEndingWith(des);
        return bookRepository.findByDescriptionContains(des);
    }

    /**
     * 自定義查詢
     *
     * @param len
     * @return
     */
    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }

    /**
     * 自定義更新
     *
     * @param status
     * @param id
     * @return
     */
    @Transactional
    public int updateByJPQL(int status, long id) {
        return bookRepository.updateByJPQL(status, id);
    }

    /**
     * 自定義刪除
     *
     * @param id
     * @return
     */
    @Transactional
    public int deleteByJPQL(long id) {
        return bookRepository.deleteByJPQL(id);
    }

    /**
     * 測試事務操作方法
     *
     * @param id
     * @param status
     * @param uid
     * @return
     */
    @Transactional
    public int deleteAndUpdate(long id, int status, long uid) {

        int dcount = bookRepository.deleteByJPQL(id);
        int ucount = bookRepository.updateByJPQL(status, uid);

        return dcount + ucount;
    }

    /**
     * 獲取一個書單訊息
     *
     * @param id
     * @return
     */
    public Book getBookById(Long id) {
        Book book = bookRepository.getBookById(id);
        if(book == null){
            throw new BookNotFoundException("書單訊息不存在");
        }
        return book;
    }
}
