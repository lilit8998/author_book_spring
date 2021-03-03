package am.itspace.author_book_spring.controller;


import am.itspace.author_book_spring.model.Book;
import am.itspace.author_book_spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String homePage(ModelMap modelMap){
        List<Book> all = bookRepository.findAll();
        modelMap.addAttribute("book",all);
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam ("keyword") String keyword,
                         ModelMap modelMap){
        if (keyword == null || keyword.length() == 0){
            return "redirect:/";
        }
        List<Book> allByName = bookRepository.findAllByNameStartingWith(keyword);
        modelMap.addAttribute("book", allByName);
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }

}
