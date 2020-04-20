import { Injectable } from '@angular/core'
import { Cliente } from './cliente';
import { Observable, of } from 'rxjs';
import { ARREGLO_CLIENTES } from './clientes.json';
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class ClientesService{

    private url: string = 'http://localhost:8080/cliente/listar';
    private urlAgregar: string = 'http://localhost:8080/cliente/agregar';
    private urlActualizar: string = 'http://localhost:8080/cliente/actualizar';
    private urlBorrar: string = 'http://localhost:8080/cliente/borrar';

    private httpHeader = new HttpHeaders({
        'Content-Type' : 'application/json'
    });

    constructor(private http: HttpClient){ }

    getClientes(): Observable<Cliente[]>{
        //return of(ARREGLO_CLIENTES);
        return this.http.get<Cliente[]>(this.url);
    }

    create(cliente: Cliente): Observable<Cliente> {
        return this.http.post<Cliente>(this.urlAgregar, cliente, {headers: this.httpHeader});
    }

    getClienteParaActualizar(id): Observable<Cliente>{
        //Concatenamos a la url de consulta el id
        return this.http.get<Cliente>(`${this.url}/${id}`);
    }

    update(cliente: Cliente): Observable<Cliente>{
        return this.http.put<Cliente>(`${this.urlActualizar}/${cliente.id}`, cliente, {headers: this.httpHeader});
    }

    delete(id: number): Observable<Cliente>{
        return this.http.delete<Cliente>(`${this.urlBorrar}/${id}`, { headers : this.httpHeader});
    }
}