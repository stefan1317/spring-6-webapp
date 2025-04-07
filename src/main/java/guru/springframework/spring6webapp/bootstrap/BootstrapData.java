package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = Author.builder()
                .firstname("Eric")
                .lastname("Evans")
                .books(new HashSet<>())
                .build();

        Book ddd = Book.builder()
                .title("Domain Driven Design")
                .isbn("123456")
                .build();

        Author rod = Author.builder()
                .firstname("Rod")
                .lastname("Johnson")
                .books(new HashSet<>())
                .build();

        Book noEJB = Book.builder()
                .title("J2EE Development without EJB")
                .isbn("6277231")
                .build();

        Publisher firstPublisher = Publisher.builder()
                .publisherName("Publisher")
                .state("Chicago")
                .zip("0123991")
                .city("IDK")
                .address("Address")
                .build();

        Publisher secondPublisher = Publisher.builder()
                .publisherName("Publisher2")
                .state("Los Angeles")
                .zip("1294832")
                .city("IDK2")
                .address("Address2")
                .build();

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        Publisher publisher = publisherRepository.save(firstPublisher);
        publisherRepository.save(secondPublisher);

        dddSaved.setPublisher(publisher);
        noEJBSaved.setPublisher(publisher);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
