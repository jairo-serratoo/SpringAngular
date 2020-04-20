import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Instructor } from './instructor';

@Injectable()
export class InstructorService{

    private urlGetInstructor: string = 'http://localhost:8080/instructor/listar';
    private urlPostInstructor: string = 'http://localhost:8080/instructor/agregar'
    private urlActualizar: string = 'http://localhost:8080/instructor/update';
    private urlBorrar: string = 'http://localhost:8080/instructor/borrar';

    private httpHeader = new HttpHeaders({
        'Content-Type' : 'application/json'
    });

    constructor(private httpCliente: HttpClient){ }

    getInstructor(): Observable<Instructor[]>{
        return this.httpCliente.get<Instructor[]>(this.urlGetInstructor);
    }

    create(instructor: Instructor): Observable<Instructor> {
        return this.httpCliente.post<Instructor>(this.urlPostInstructor, instructor, {headers: this.httpHeader})
    }

    getUnInstructor(id): Observable<Instructor>{
        return this.httpCliente.get<Instructor>(`${this.urlGetInstructor}/${id}`);
    }

    actualizar(instructor: Instructor): Observable<Instructor>{
        return this.httpCliente.put<Instructor>(`${this.urlActualizar}/${instructor.id}`,instructor, {headers: this.httpHeader});
    }

    borrar(id: number): Observable<Instructor>{
        return this.httpCliente.delete<Instructor>(`${this.urlBorrar}/${id}`, {headers : this.httpHeader});
    }
     
}