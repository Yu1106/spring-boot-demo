package com.jacky;

import com.alibaba.fastjson.JSON;
import com.jacky.domain.Author;
import com.jacky.domain.AuthorRepository;
import com.jacky.domain.Wallet;
import com.jacky.domain.WalletRepository;
import com.jacky.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class AuthorTests {

    @Autowired
    public AuthorRepository authorRepository;

    @Autowired
    public WalletRepository walletRepository;

    @Autowired
    public AuthorService authorService;

    @Test
    public void saveAuthorTest() {
        Author author = new Author();
        author.setNickName("jacky");
        author.setPhone("0921160563");
        author.setSignDate(new Date());
        author.setWallet(new Wallet(new BigDecimal(188.23)));
        authorRepository.save(author);
    }

    @Test
    public void findWalletTest(){
        Wallet wallet = walletRepository.findById(13L).orElse(null);
        System.out.println(JSON.toJSONString(wallet, true));
    }

    @Test
    public void updateAuthor() {
        Author author = authorService.findAuthor(6L);
        author.setPhone("22566165");
        Wallet wallet = author.getWallet();
        wallet.setBalance(new BigDecimal(288.88));
        author.setWallet(wallet);
        authorService.updateAuthor(author);
    }

    @Test
    public void findAuthorTest() {
        Author author = authorService.findAuthor(6L);
        System.out.println(JSON.toJSONString(author, true));
    }

    @Test
    public void deleteAuthorTest() {
        authorService.deleteAuthor(4L);
    }

    //    @Test
//    public void findAuthorTest() {
//        List<Author> authors = authorRepository.findByPhoneAndNickName("0921160563", "Jacky");
//        List<Author> authors = authorRepository.findDistinctByNickNameIgnoreCaseAndPhoneOrderBySignDateDesc("Jacky", "0921160563");
//        List<Author> authors = authorRepository.findDistinctByNickNameIgnoreCaseOrPhoneOrderBySignDateDesc("Jacky", "0921160563");
//        List<Author> authors = authorRepository.findByNickNameLike("%Ja%");
//        List<Author> authors = authorRepository.findByPhone("563");
//        List<Object[]> array = authorRepository.findByArray("Ja");
//        List<Author> authors = authorRepository.findByNickName("Ja", Sort.by(Sort.Direction.DESC,"signDate"));
//        List<Author> authors = authorRepository.findBySql("Ja");
//        System.out.println(JSON.toJSONString(authors, true));

//        int i = authorRepository.setNickName("Jack", "22566165");
//        System.out.println(i);
//    }

    @Test
    public void findAuthorForPageTest() {
//        127.0.0.1:8080/authors?page=1&size=2&sortid,desc
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 5, sort);
        Page<Author> page = authorRepository.findAll(pageable);
        System.out.println(JSON.toJSONString(page, true));
    }

    @Test
    public void transactionalTest() {
//        authorService.updateAuthor();
    }
}
