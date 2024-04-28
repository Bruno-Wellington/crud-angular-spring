import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form: UntypedFormGroup;

  constructor(private FormBuilder: UntypedFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
  private location: Location) {
    this.form = this.FormBuilder.group({
      name: [null],
      category: [null]
    })
  }

  ngOnInit(): void {}

  onSubmit(){
    this.service.save(this.form.value).subscribe({
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
