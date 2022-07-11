package net.lvs.demo_graphql.service.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.lvs.demo_graphql.model.Book;
import net.lvs.demo_graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookQuery implements GraphQLQueryResolver {
    @Autowired
    private  BookRepository bookRepository;
    public Book findBookById(String id) {
        return bookRepository.findById(id).get();
    }
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

}