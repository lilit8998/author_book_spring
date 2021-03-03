package am.itspace.author_book_spring.repository;

import am.itspace.author_book_spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByNameStartingWith(String name);

}
