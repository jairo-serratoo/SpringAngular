import { Component, OnInit } from '@angular/core';
import { Instructor } from '../instructor';
import { InstructorService } from '../instructor.service';
import Swal from 'sweetalert2'

@Component({
    selector: 'instructorget-app',
    templateUrl: 'instructorget.component.html'
})
export class InstructorGetComponent implements OnInit{

    public instructor: Instructor[];

    constructor(private instructorService: InstructorService){ }

    //Es el metodo que se ejecuta al invocar el componente
    ngOnInit() {
        //Se suscribe el metodo  
        this.instructorService.getInstructor().subscribe(
            //Funcion Anonima 
            (instructorParam) => this.instructor = instructorParam
        );
    }

    borrar(instructor: Instructor): void{
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
              confirmButton: 'btn btn-success',
              cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })
          
        swalWithBootstrapButtons.fire({
            title: 'Estas seguro?',
            text: "No se podran revertir los cambios!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Si, borralo!',
            cancelButtonText: 'No, cancelar!',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {
                this.instructorService.borrar(instructor.id).subscribe(
                    response => {
                        this.instructor = this.instructor.filter(ins => ins !== instructor)

                        swalWithBootstrapButtons.fire(
                            'Borrado!',
                            `El instructor ${instructor.nombre} ${instructor.apellidoP} ha sido borrado.`,
                            'success'
                        )
                    }
                )
            }
        })
    }

}