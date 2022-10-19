package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Starting in Bootstrap");

    // Publishers
    Publisher publisher = new Publisher();
    publisher.setName("SFG Publishing");
    publisher.setCity("St Petersburg");
    publisher.setState("FL");

    publisherRepository.save(publisher);

    // Authors
    Author eric = new Author("Eric", "Evans");
    Author rod = new Author("Rod", "Johnson");

    // Books
    Book ddd = new Book("Domain Driven Design", "123123");
    Book noEJB = new Book("J2EE Development without EJB", "567567");

    // Set books with publisher
    ddd.setPublisher(publisher);
    noEJB.setPublisher(publisher);

    // Set publishers with books
    publisher.getBooks().add(ddd);
    publisher.getBooks().add(noEJB);

    // Set authors with books
    eric.getBooks().add(ddd);
    rod.getBooks().add(noEJB);

    // Set books with authors
    ddd.getAuthors().add(eric);
    noEJB.getAuthors().add(rod);

    // Create authors
    authorRepository.save(eric);
    authorRepository.save(rod);

    // Create books
    bookRepository.save(ddd);
    bookRepository.save(noEJB);

    System.out.println("Number of Books: " + bookRepository.count());
    System.out.println("Number of Authors: " + authorRepository.count());
    System.out.println("Number of Publishers: " + publisherRepository.count());
  }
}
