package com.firstprogram.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DiaryRepository extends JpaRepository<Diary,Long>
{
List<Diary> findByAuthor(String author);


}
