package com.kverchi.diary.repository;

import com.kverchi.diary.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Liudmyla Melnychuk on 3.6.2019.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
