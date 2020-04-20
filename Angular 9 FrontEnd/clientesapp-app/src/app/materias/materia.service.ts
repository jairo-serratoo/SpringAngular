import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Materia } from './materia';

@Injectable()
export class MateriaService {

    private urlMateria: string = 'http://localhost:8080/materias/listar';
    private urlAgregarMateria = 'http://localhost:8080/materias/agregar';
    private urlActualizarMateria = 'http://localhost:8080/materias/actualizar';
    private urlBorrarMateria = 'http://localhost:8080/materias/borrar';

    private httpHeader = new HttpHeaders({
        'Content-Type' : 'application/json'
    });

    constructor(private http: HttpClient) { }

    //Observable es para serializar el paso de la informacion entre el back y el front
    getMaterias(): Observable<Materia[]>{
        //a traves del cliente http con su verbo get, un arreglo de Materia que me retorna el endpoint de spring 
        return this.http.get<Materia[]>(this.urlMateria);
    }

    postMaterias(materia: Materia): Observable<Materia>{
        return this.http.post<Materia>(this.urlAgregarMateria, materia, {headers: this.httpHeader});   
    }

    getMateria(id): Observable<Materia>{
        return this.http.get<Materia>(`${this.urlMateria}/${id}`);
    }

    putMaterias(materia: Materia): Observable<Materia>{
        return this.http.put<Materia>(`${this.urlActualizarMateria}/${materia.id}`, materia, {headers: this.httpHeader});
    }

    deleteMaterias(id: number): Observable<Materia>{
        return this.http.delete<Materia>(`${this.urlBorrarMateria}/${id}`, {headers : this.httpHeader});
    }
}