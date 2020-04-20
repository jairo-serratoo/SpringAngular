import { Component, OnInit } from '@angular/core';
import { Instructor } from '../instructor';
import { InstructorService } from '../instructor.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2'

@Component({
    selector: 'instructoradd-app',
    templateUrl: './form.instructor.component.html'
})
export class AgregarInstructor implements OnInit {

    public instructor: Instructor = new Instructor();
    public titulo: string = 'Agregar Instructor';

    constructor(
        private instructorService: InstructorService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.cargarInstructor();
    }

    cargarInstructor(): void {
        this.activatedRoute.params.subscribe(
            params => {
                let id = params['id'];
                if (id) {
                    this.instructorService.getUnInstructor(id).subscribe(
                        (instructor) => this.instructor = instructor
                    );
                }
            }
        )
    }

    create(): void {
        this.instructorService.create(this.instructor).subscribe(
            instructor => {
                this.router.navigate(['instructorget'])
                swal.fire('Nuevo Instructor', `Creado instructor ${instructor.nombre} con exito!`, 'success')
            }
        );
    }

    update(): void{
        this.instructorService.actualizar(this.instructor).subscribe(
            instructor => {
                this.router.navigate(['instructorget'])
                swal.fire('Instructor Actualizado', `Instructor ${instructor.nombre} actualizado con exito!!`, 'success');
            }
        );
    }
}