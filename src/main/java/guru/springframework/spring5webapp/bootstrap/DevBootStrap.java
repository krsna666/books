package guru.springframework.spring5webapp.bootstrap;


import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        initData();
    }

    private void initData(){

        Publisher publisher1 = new Publisher();
        publisher1.setName("foo");
        publisher1.setAddress("mexico");

        publisherRepository.save(publisher1);


        Publisher publisher2 = new Publisher();
        publisher2.setName("Wrox");
        publisher2.setAddress("estados unidos");

        publisherRepository.save(publisher2);


        //eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain drivern design", "1234",publisher1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE DEvelopment without EJB", "2345",publisher2);
        eric.getBooks().add(noEJB);
        //ddd.getAuthors().add(eric);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }


}
