package am.itspace.author_book_spring.controller;


import am.itspace.author_book_spring.model.Author;
import am.itspace.author_book_spring.model.Book;
import am.itspace.author_book_spring.repository.AuthorRepository;
import am.itspace.author_book_spring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/task/save")
    public String saveAuthor(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap) {

        if (id != null) {
            modelMap.addAttribute("member", bookRepository.getOne(id));
        }else{
            modelMap.addAttribute("member", new Author());
        }
        modelMap.addAttribute("members", authorRepository.findAll());
        return "saveMember";

    }

    @PostMapping("/task/save")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/task/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

}
