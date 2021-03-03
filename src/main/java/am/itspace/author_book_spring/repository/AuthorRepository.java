package am.itspace.author_book_spring.repository;


import am.itspace.author_book_spring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {


}
