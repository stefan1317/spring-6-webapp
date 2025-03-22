package bootstrap;

import domain.Author;
import domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repositories.AuthorRepository;
import repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = Author.builder()
                .firstname("Eric")
                .lastname("Evans")
                .build();

        Book ddd = Book.builder()
                .title("Domain Driven Design")
                .isbn("123456")
                .build();

        Author rod = Author.builder()
                .firstname("Rod")
                .lastname("Johnson")
                .build();

        Book noEJB = Book.builder()
                .title("J2EE Development without EJB")
                .isbn("6277231")
                .build();

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
    }
}
