package net.lvs.demo_graphql.service.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.lvs.demo_graphql.model.Author;
import net.lvs.demo_graphql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorQuery implements GraphQLQueryResolver {


    @Autowired
    private  AuthorRepository authorRepository;

    public Author findAuthorById(String id) {
        return authorRepository.findById(id).get();
    }

    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }


}