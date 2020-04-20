import { Component, OnInit } from '@angular/core';
import { Materia } from '../materia';
import { MateriaService } from '../materia.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2'

@Component({
    selector: 'agregarmateria-app',
    templateUrl: 'form.materias.component.html'
})
export class FormAgregarMateria implements OnInit {
    public titulo: string = 'Agregar Materias';

    public materia: Materia = new Materia();

    constructor(
        private materiaService: MateriaService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.cargarMateria();
    }

    //Obtengo la materia del id que le paso
    cargarMateria(): void {
        this.activatedRoute.params.subscribe(
            params => {
                let id = params['id'];
                if (id) {
                    this.materiaService.getMateria(id).subscribe(
                        (materia) => this.materia = materia
                    );
                }
            }
        )
    }

    agregar(): void {
        console.log(this.materia);
        this.materiaService.postMaterias(this.materia).subscribe(
            materia => {
                this.router.navigate(['materias'])
                swal.fire('Nueva Materia', `Materia ${materia.nombre} creada con exito!!`, 'success')
            }
        );
    }

    actualizar(): void {
        console.log(this.materia);
        this.materiaService.putMaterias(this.materia).subscribe(
            (materia) => {
                this.router.navigate(['materias'])
                swal.fire('Materia Actualizada', `Matera ${materia.nombre} actualizada con exito!!`, 'success')
            }
        )
    }
}