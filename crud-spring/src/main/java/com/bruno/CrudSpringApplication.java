package com.bruno;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bruno.enums.Category;
import com.bruno.model.Course;
import com.bruno.model.Lesson;
import com.bruno.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular");
			c.setCategory(Category.FRONT_END);

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("watch?v=-1Y");
			l.setCourse(c);
			c.getLessons().add(l);

			courseRepository.save(c);
		};
	}

}
