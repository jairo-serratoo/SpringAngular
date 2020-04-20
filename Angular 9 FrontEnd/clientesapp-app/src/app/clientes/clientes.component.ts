import { Component, OnInit } from '@angular/core';
import { ClientesService } from './clientes.service';
import { Cliente } from './cliente';
import Swal from 'sweetalert2'

@Component({
    selector: 'clientes-app',
    templateUrl: './clientes.component.html',
    styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit{

    arregloClientes: Cliente[];

    constructor(private clienteService: ClientesService){ }

    ngOnInit() {
        this.clienteService.getClientes().subscribe(
            //Funcion anonima
            (clientes) => this.arregloClientes = clientes
        );
    }

    borrar(cliente: Cliente): void {
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
                this.clienteService.delete(cliente.id).subscribe(
                    response => {
                        this.arregloClientes = this.arregloClientes.filter(cli => cli !== cliente)

                        swalWithBootstrapButtons.fire(
                            'Borrado!',
                            `El usuario ${cliente.nombre} ${cliente.apellidoP} ha sido borrado.`,
                            'success'
                        )
                    }
                )
            }
        })
    }
}