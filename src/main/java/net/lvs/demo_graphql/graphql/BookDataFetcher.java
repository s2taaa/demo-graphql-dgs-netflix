package net.lvs.demo_graphql.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import net.lvs.demo_graphql.model.Author;
import net.lvs.demo_graphql.model.Book;
import net.lvs.demo_graphql.model.dto.BookDTO;
import net.lvs.demo_graphql.repository.AuthorRepository;
import net.lvs.demo_graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * 10:35 AM 11-Jul-22
 * Long Tran
 */
@DgsComponent
public class BookDataFetcher {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @DgsQuery
    public Book findBookById(String id) {
        return bookRepository.findById(id).get();
    }

    @DgsQuery
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    @DgsMutation
    public Book newBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setPage(bookDTO.getPage());
        Optional<Author> author = authorRepository.findById(bookDTO.getAuthorId());
        author.ifPresent(book::setAuthor);
        return  bookRepository.save(book);
    }
    @DgsMutation
    public boolean deleteBook(String id) {
        bookRepository.deleteById(id);
        return true;
    }
    @DgsMutation
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
