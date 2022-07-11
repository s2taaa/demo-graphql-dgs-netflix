package net.lvs.demo_graphql.service.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import net.lvs.demo_graphql.model.Author;
import net.lvs.demo_graphql.model.Book;
import net.lvs.demo_graphql.model.dto.BookDTO;
import net.lvs.demo_graphql.repository.AuthorRepository;
import net.lvs.demo_graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookMutation implements GraphQLMutationResolver {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public Book newBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setPage(bookDTO.getPage());
        Optional<Author> author = authorRepository.findById(bookDTO.getAuthorId());
        author.ifPresent(book::setAuthor);
        return  bookRepository.save(book);
    }
    public boolean deleteBook(String id) {
        bookRepository.deleteById(id);
        return true;
    }
    public Book updateBook(String id,String name,int page) {
        Optional<Book> opt = bookRepository.findById(id);
        if (opt.isPresent()) {
            Book book = opt.get();
            book.setPage(page);
            book.setName(name);
            bookRepository.save(book);
            return book;
        }
        return null;
    }
}