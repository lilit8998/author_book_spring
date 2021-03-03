package am.itspace.author_book_spring.controller;


import am.itspace.author_book_spring.model.Author;
import am.itspace.author_book_spring.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Value("${author.upload.dir}")
    private String uploadDir;

    @GetMapping("/author/save")
    public String saveAuthor(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap) {

        if (id != null) {
            modelMap.addAttribute("author", authorRepository.getOne(id));
        } else {
            modelMap.addAttribute("author", new Author());

        }
        return "saveAuthor";

    }

    @PostMapping("/author/save")
    public String addMember(@ModelAttribute Author author,
                            @RequestParam("image") MultipartFile image) throws IOException {
        if (image != null) {
            String photoUrl = System.currentTimeMillis() +
                    "_" + image.getOriginalFilename();
            File file = new File(uploadDir + File.separator + photoUrl);
            image.transferTo(file);
            author.setPhotoUrl(photoUrl);
        }
        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping(value = "/author/image")
    public @ResponseBody
    byte[] getImage(@RequestParam("photoUrl") String photoUrl) throws IOException {

        InputStream in = new FileInputStream(uploadDir + File.separator + photoUrl);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/author/delete")
    public String deleteMember(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/";
    }

}

