package org.agoncal.fascicle.quarkus.book;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.jboss.logging.Logger;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {
  private static final Logger LOGGER = Logger.getLogger(BookService.class);
  @Inject
  EntityManager em;

  public Book persistBook(@Valid Book book) {
    Book.persist(book);
    return book;
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findAllBooks() {
    return Book.listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Book> findBookById(Long id) {
    return Book.findByIdOptional(id);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Book findRandomBook() {
    Book randomBook = null;
    while (randomBook == null) {
      randomBook = Book.findRandom();
    }
    return randomBook;
  }

  public Book updateBook(@Valid Book book) {
    Book entity = em.merge(book);
    return entity;
  }

  public void deleteBook(Long id) {
    Book.deleteById(id);
  }
}
