package com.firstprogram.demo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class DiaryController {

    

    @Autowired
	DiaryRepository diaryRepository;

    @GetMapping("/greeting")
	public String greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		System.out.println("==== get greeting ====");
		return new String("Hello world");
    }
    
    @GetMapping("/diaries")
	public ResponseEntity<List<Diary>> getAllDiaries(@RequestParam(required = false) String author) {
		try {
            System.out.println("came in1");
			List<Diary> diaries = new ArrayList<Diary>();
            //System.out.println("diaries = " + Arrays.toString(diaries));
			if (author== null)
            {	
                System.out.println("came in2 author is null");
				diaryRepository.findAll().forEach(diaries::add);
                System.out.println("diaries: " + diaries);
            }
			else
            System.out.println("else came in2 author is not null");
				diaryRepository.findByAuthor(author).forEach(diaries::add);
            
			if (diaries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(diaries, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}

}   
