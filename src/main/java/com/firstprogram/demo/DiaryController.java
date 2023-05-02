package com.firstprogram.demo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import jakarta.persistence.Id;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class DiaryController {

    

    @Autowired
	DiaryRepository diaryRepository;

	@Autowired
	AuthorRepository authorRepository;

    @GetMapping("/diaries")
	public ResponseEntity<List<Diary>> getAllDiaries(@RequestParam(required = false) String author) {
		try {
			List<Diary> diaries = new ArrayList<Diary>();
			if (author== null)
            {	

				diaryRepository.findAll().forEach(diaries::add);
            }
			else
				diaryRepository.findByAuthor(author).forEach(diaries::add);
            
			if (diaries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(diaries, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}


	@GetMapping("/diaries/{id}")
	public ResponseEntity<?> getDiaryById(@PathVariable("id") long id)
	{
		try {
			Diary diary = diaryRepository.findById(id).get();
			return new ResponseEntity<>(new ResponseMessage(diary, null), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new ResponseMessage(new AppError(HttpStatus.NOT_FOUND, "Не найдено")),HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/diaries/{id}")
	public ResponseEntity<?> replaceDiary(@RequestBody Diary newDiary, @PathVariable long id)
	{
		try {	
			Diary curDiary = diaryRepository.findById(id).get();
			curDiary.setAuthor(newDiary.getAuthor());
			curDiary.setSubject(newDiary.getSubject());
			curDiary.setText(newDiary.getText());
			curDiary.setUpdateDate(new Date());
			diaryRepository.save(curDiary);
			return new ResponseEntity<>(new ResponseMessage(curDiary, null), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new ResponseMessage(new AppError(HttpStatus.NOT_FOUND, "Не найдено")),HttpStatus.NOT_FOUND);
		}
	}



	@PostMapping("/diaries")
	public ResponseEntity<?> createTutorial(@RequestBody Diary diary) {
		try {

			// Author author1 = authorRepository.findById(diary.getAuthorId()).get();
			// Diary diary2 = diaryRepository.save(new Diary(author1.getId(), diary.getSubject(),diary.getText()));

			Diary diary2 = diaryRepository.save(new Diary(diary.getAuthor(), diary.getSubject(),diary.getText()));

			return new ResponseEntity<>(new ResponseMessage(diary2, null),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage(diary,new AppError(HttpStatus.BAD_REQUEST, "Какая-то ошибка")), HttpStatus.BAD_REQUEST);
	}
}

		@DeleteMapping("/diaries/{id}")
		public ResponseEntity<?> deleteDiary(@PathVariable("id") long id) {
			try {
				diaryRepository.deleteById(id);
				return new ResponseEntity<>(new ResponseMessage (), HttpStatus.ACCEPTED);
				//return new ResponseEntity<>(new ResponseMessage(null,new AppError(HttpStatus.ACCEPTED, "Удалено")), HttpStatus.ACCEPTED);
			} 
			catch (NoSuchElementException e) 
			{
				return new ResponseEntity<>(new ResponseMessage((Author)null, new AppError(HttpStatus.NOT_FOUND, "Нет того, что вы ищете, сударыня")), HttpStatus.BAD_REQUEST);
			}
			catch (Exception e) 
			{
				return new ResponseEntity<>(new ResponseMessage((Author)null, new AppError(HttpStatus.BAD_REQUEST, "Нет того, что вы ищете, сударыня")), HttpStatus.BAD_REQUEST);
		}
	}
}
	

