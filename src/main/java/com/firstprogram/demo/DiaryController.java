package com.firstprogram.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class DiaryController {

    

    @Autowired
	DiaryRepository diaryRepository;

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


	// @PostMapping("/diaries")
	// public ResponseEntity<?> createTutorial1(@RequestBody Diary diary) {
	// 	try {
	// 		Diary diary2 = diaryRepository.save(new Diary(diary.getAuthor(), diary.getSubject(), diary.getText()));
	// 		return new ResponseEntity<>(new ResponseMessage(diary2.getId(), diary2.getAuthor()), HttpStatus.CREATED);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(new ResponseMessage(diary.getId(), diary.getAuthor(),new AppError(HttpStatus.BAD_REQUEST, "Какая-то ошибка")), HttpStatus.BAD_REQUEST);
			
	// 	}
	// }
	@PostMapping("/diaries")
	public ResponseEntity<?> createTutorial1(@RequestBody Diary diary) {
		try {
			Diary diary2 = diaryRepository.save(new Diary(diary.getAuthor(), diary.getSubject(), diary.getText()));
			return new ResponseEntity<>(new ResponseMessage(diary2, null),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage(diary,new AppError(HttpStatus.BAD_REQUEST, "Какая-то ошибка")), HttpStatus.BAD_REQUEST);
			
		}
	}

		// @DeleteMapping("/diaries/{id}")
		// final ResponseEntity<?> deleteDiary(@PathVariable("id") long id) {
		// 	try {
		// 		Diary diary3 = diaryRepository.findById(id).get();
		// 		diaryRepository.deleteById(id);
		// 		return new ResponseEntity<>(new ResponseMessage(diary3.getId(), diary3.getAuthor()), HttpStatus.ACCEPTED);
		// 	} 
		// 	catch (NoSuchElementException e) {
		// 		Diary diary3 = diaryRepository.findById(id).get();
		// 		return new ResponseEntity<>(new ResponseMessage(diary3.getId(), diary3.getAuthor(),new AppError(HttpStatus.NOT_FOUND, "Нет того, что вы ищете, сударыня")), HttpStatus.BAD_REQUEST);
		// 	}
		// 	catch (Exception e) {
		// 		Diary diary3 = diaryRepository.findById(id).get();
		// 		return new ResponseEntity<>(new ResponseMessage(diary3.getId(), diary3.getAuthor(),new AppError(HttpStatus.BAD_REQUEST, "Какая-то ошибка")), HttpStatus.BAD_REQUEST);
		// 	}
		// }

		@DeleteMapping("/diaries/{id}")
		final ResponseEntity<?> deleteDiary(@PathVariable("id") long id) {
			try {
				Diary diary3 = diaryRepository.findById(id).get();
				diaryRepository.deleteById(id);
				return new ResponseEntity<>(new ResponseMessage(null,new AppError(HttpStatus.ACCEPTED, "Удалено")), HttpStatus.ACCEPTED);
			} 
			catch (NoSuchElementException e) 
			{
				// Diary diary3 = diaryRepository.findById(id).get();
				return new ResponseEntity<>(new ResponseMessage(null,new AppError(HttpStatus.NOT_FOUND, "Нет того, что вы ищете, сударыня")), HttpStatus.BAD_REQUEST);
			}
			catch (Exception e) 
			{
				// Diary diary3 = diaryRepository.findById(id).get();
				return new ResponseEntity<>(new ResponseMessage(null,new AppError(HttpStatus.BAD_REQUEST, "Какая-то ошибка")), HttpStatus.BAD_REQUEST);
			}
		}
	
}
