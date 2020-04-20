import { Component, OnInit } from '@angular/core';
import { MateriaService } from './materia.service';
import { Materia } from './materia';
import Swal from 'sweetalert2';

@Component({
    selector: 'materia-app',
    templateUrl: 'materia.component.html'
})
export class MateriaComponent implements OnInit{

    arregloMateria: Materia[];

    constructor(private materiaService: MateriaService){ }

    ngOnInit() {
        this.materiaService.getMaterias().subscribe(
            (materiadelServicio) => this.arregloMateria = materiadelServicio
        ) 
    }

    borrarMateria(materia: Materia): void{
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
              confirmButton: 'btn btn-success',
              cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })
          
        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {
                this.materiaService.deleteMaterias(materia.id).subscribe(
                    response => {
                        this.arregloMateria = this.arregloMateria.filter(mat => mat !== materia)
                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            `${materia.nombre} has been deleted.`,
                            'success'
                        )
                    }
                )
            } 
        })
    }
}