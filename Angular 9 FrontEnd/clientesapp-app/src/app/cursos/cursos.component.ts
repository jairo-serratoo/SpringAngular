import { Component } from '@angular/core';

@Component({
    selector: 'cursos-component',
    templateUrl: './cursos.component.html',
    styleUrls: ['./cursos.component.css']
})
export class CursosComponent{

    listaCursos: string[] = ['Java', 'HTML5', 'Angular', 'Spring'];
    habilitar: boolean = true;
    boton: string;


    constructor(){

    }

    setHabilitar(): void{
        this.habilitar = (this.habilitar == true) ? false : true
    }
}