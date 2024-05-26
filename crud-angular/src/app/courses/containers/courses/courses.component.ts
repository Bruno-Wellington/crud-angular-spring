import { Component, OnInit } from '@angular/core';
import { MatLegacyDialog as MatDialog } from '@angular/material/legacy-dialog';
import { MatLegacySnackBar as MatSnackBar } from '@angular/material/legacy-snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]> | null = null;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();

  }

  refresh(){//Atualiza a pagina
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar os cursos');
        return of([])
      })
    );
  }

  onError(errorMsg: string) { //Mensagem de erro
      this.dialog.open(ErrorDialogComponent, {
        data: errorMsg
      });
  }

  ngOnInit(): void { }

  onAdd(){
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(course: Course){
    this.router.navigate(['edit', course._id], {relativeTo: this.route});
  }

  onRemove(course: Course){
     /*Popap para confirmar a exclusão*/
     const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse curso?',
    });

    /*Codigo que faz a explusão*/
    dialogRef.afterClosed().subscribe((result: boolean) => {
      if(result){
        this.coursesService.remove(course._id).subscribe(
          () => {
            this.refresh();//Atualiza a pagina depois de remover um curso da lista
            this.snackBar.open('Curso removido com sucesso!', 'X', {
              duration: 1500, //duração da popup
              //verticalPosition: 'top', //Mostra a popup no topo
              horizontalPosition: 'center'// centraliza a popup
            });
          },
          () => this.onError('Erro ao tentar remover curso!')
        );
      }
    });
  }
}
