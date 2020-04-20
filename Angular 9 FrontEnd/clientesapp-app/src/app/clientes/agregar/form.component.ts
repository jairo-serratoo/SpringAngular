import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClientesService } from '../clientes.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
    selector: 'formcliente-app',
    templateUrl: './form.component.html'
})
export class FormCliente implements OnInit{

    public cliente: Cliente = new Cliente();
    public titulo: string = 'Crear Cliente';

    constructor( 
        private clienteService: ClientesService, 
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.cargarCliente();
    }

    cargarCliente(): void{
        //Suscribo un observable para recuperar los parametros del componente
        this.activatedRoute.params.subscribe(
            params => {
                //Busca en los parametros el id
                let id = params['id']
                //Validams que exista el id
                if(id){
                    //Si existe a traves del servicio suscribimos para asignar el cliente de la consulta al atributo cliente
                    this.clienteService.getClienteParaActualizar(id).subscribe(
                        (cliente) => this.cliente = cliente
                    )
                }
            }
        )
    }

    public create(): void{
        console.log(this.cliente);
        this.clienteService.create(this.cliente).subscribe(
            cliente => {
                this.router.navigate(['clientes'])
                //Mensaje de alerta
                swal.fire('Nuevo Cliente', `Cliente ${cliente.nombre} creado con exito`, 'success')
            } 
            
        )
    }

    public actualizar(): void{
        this.clienteService.update(this.cliente).subscribe(
            (cliente) => {
                this.router.navigate(['clientes'])
                swal.fire('Cliente Actualizado', `Cliente ${cliente.nombre} actualizado con exito!`, 'success')
            }
        )
    }

}