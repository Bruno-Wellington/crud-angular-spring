import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';
import { Lesson } from '../../model/lesson';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form!: FormGroup;

  constructor(private FormBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];
    this.form = this.FormBuilder.group({
      _id: [course._id],
      name: [course.name, [Validators.required,
                  Validators.minLength(5),
                  Validators.maxLength(100)]
            ],
      category: [course.category, [Validators.required]],
      lessons: this.FormBuilder.array(this.retrieveLessons(course))
    });
    console.log(this.form);
    console.log(this.form.value);
  }

  //obter aulas
  private retrieveLessons(course: Course){
    const lessons = [];
    if(course?.lessons){
      course.lessons.forEach(lesson => lessons.push(this.createLesson(lesson)))
    }else{
      lessons.push(this.createLesson());
    }
    return lessons;
  }

  private createLesson(lesson: Lesson = {id: '', name: '', youtubeUrl: ''}){
    return this.FormBuilder.group({
      id: [lesson.id],
      name: [lesson.name],
      youtubeUrl: [lesson.youtubeUrl]
    });
  }

  onSubmit(){
    this.service.save(this.form.value)
    .subscribe({
      next:(result) => this.onSuccess(),
      error:() =>{this.onError()},
    });
  }

  onCancel(){
    this.location.back();
  }

  private onSuccess(){
    this.snackBar.open('Curso salvo com sucesso!', '', { duration: 1500 });
    this.onCancel();
  }

  private onError(){
    this.snackBar.open('Erro ao salvar curso!', '', { duration: 1500 });
  }

  getErrorMessage(fieldName: string){
    const field = this.form.get(fieldName);

    if(field?.hasError('required')){
      return 'Campo obrigatorio.';
    }

    if(field?.hasError('minlength')){
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `O tamanho minimo de caracteres é [ ${requiredLength} ].`;
    }

    if(field?.hasError('maxlength')){
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 200;
      return `O tamanho maximo de caracteres é [ ${requiredLength} ].`;
    }

    return 'Campo invalido';
  }
}
