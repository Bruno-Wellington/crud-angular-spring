import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form = this.FormBuilder.group({
    _id: [''],
    name: [''],
    category: ['']
  });

  constructor(private FormBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];
    this.form.setValue({
      _id: course._id,
      name: course.name,
      category: course.category,
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
}
