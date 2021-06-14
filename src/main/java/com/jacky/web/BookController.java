package com.jacky.web;

import com.jacky.domain.Book;
import com.jacky.exception.BookNotFoundException;
import com.jacky.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * 獲取書單列表
     *
     * @param model
     * @return
     */
    @GetMapping("/books")
    public String list(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
//        List<Book> books = bookService.findAll();
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Book> page1 = bookService.findAllByPage(pageable);
        model.addAttribute("page", page1);
        return "books";
    }

    /**
     * 獲取書單詳情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "book";
    }

    /**
     * 跳轉input提交頁面
     *
     * @return
     */
    @GetMapping("/books/input")
    public String inputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }

    /**
     * 跳轉道更新頁面input
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}/input")
    private String inputEditPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "input";
    }

    /**
     * 提交一個書單訊息
     *
     * @param book
     * @return
     */
    /**
     * POST--->redirect--->GET
     */
    @PostMapping("/books")
    public String post(Book book, final RedirectAttributes attributes) {
        Book book1 = bookService.save(book);
        if (book1 != null) {
            attributes.addFlashAttribute("message", "(" + book1.getName() + ")信息提交成功");
        }
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes attributes) {
        bookService.deleteOne(id);
        attributes.addFlashAttribute("message", "刪除成功");
        return "redirect:/books";
    }

    /**
     * 異常處理
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/deal-with-error/books/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }

//    /**
//     * 異常處理
//     *
//     * @param request
//     * @param e
//     * @return
//     */
//    @ExceptionHandler({BookNotFoundException.class, SQLException.class})
//    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {
//        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(), e.getMessage());
//
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//            throw e;
//        }
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url", request.getRequestURI());
//        mav.addObject("exception", e);
//        mav.setViewName("error/error");
//
//        return mav;
//    }
}
