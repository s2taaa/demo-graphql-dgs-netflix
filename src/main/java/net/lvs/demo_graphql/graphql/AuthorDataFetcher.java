package net.lvs.demo_graphql.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import net.lvs.demo_graphql.model.Author;
import net.lvs.demo_graphql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * 10:36 AM 11-Jul-22
 * Long Tran
 */
@DgsComponent
public class AuthorDataFetcher {

    @Autowired
    private AuthorRepository authorRepository;

    @DgsQuery
    public Author findAuthorById(String id) {
        return authorRepository.findById(id).get();
    }

    @DgsQuery
    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }

    @DgsMutation
    public Author createAuthor(Author author) {
        return  authorRepository.save(author);
    }

    @DgsMutation
    public boolean deleteAuthor(String id) {
        authorRepository.deleteById(id);
        return true;
    }

    @DgsMutation
    public Author updateAuthor( String id,String firstName, String lastName) {
        Optional<Author> opt = authorRepository.findById(id);
        if (opt.isPresent()) {
            Author author = opt.get();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authorRepository.save(author);
            return author;
        }
        return null;
    }
}
