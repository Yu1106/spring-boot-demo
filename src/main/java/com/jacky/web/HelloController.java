package com.jacky.web;

import com.jacky.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@Controller
@RestController
@RequestMapping("/api/v2")
public class HelloController {

    //@RequestMapping(value = "/say", method = RequestMethod.GET)
    @PostMapping(value = "/say")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/books")
//    @ResponseBody
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {

        Map<String, Object> book = new HashMap<>();
        book.put("name", "余建志");
        book.put("isbn", "0921160563");
        book.put("author", "余建志");
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "余建志2");
        book2.put("isbn", "0921160563");
        book2.put("author", "余建志2");

        List<Map<String, Object>> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("page", page);
        pagemap.put("size", size);
        pagemap.put("contents", contents);

        return pagemap;
    }

    /**
     * 正則表達式: {參數名:政則表達式}
     *
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id) {

        return null;
    }

    @PostMapping("/books")
    public Object post(@RequestParam("name") String name, @RequestParam("author") String author, @RequestParam("isbn") String isbn) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);

        return book;
    }
}
