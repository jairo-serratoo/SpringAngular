import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClientesService } from './clientes/clientes.service';
import { CursosComponent } from './cursos/cursos.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'
import { MateriaComponent } from './materias/materia.component';
import { MateriaService } from './materias/materia.service';
import { FormCliente } from './clientes/agregar/form.component';
import { FormsModule } from '@angular/forms'
import { FormAgregarMateria } from './materias/agregar/form.materias.component';
import { InstructorGetComponent } from './instructores/consulta/instructorget.component';
import { InstructorService } from './instructores/instructor.service';
import { AgregarInstructor } from './instructores/agregar/form.instructor.component';
import { FooterComponent } from './footer/footer.component';

const routes: Routes = [
  {path: '', redirectTo: '/cursos', pathMatch:'full'},
  {path:'cursos', component: CursosComponent},
  {path:'clientes', component: ClientesComponent},
  {path: 'materias', component: MateriaComponent},
  {path: 'agregarcliente', component: FormCliente},
  {path: 'agregarmateria', component: FormAgregarMateria},
  {path: 'instructorget', component: InstructorGetComponent},
  {path: 'instructorpost', component: AgregarInstructor},
  //Actualizar
  {path: 'agregarcliente/:id', component: FormCliente},
  {path: 'updatemateria/:id', component: FormAgregarMateria},
  {path: 'updateinstructor/:id', component: AgregarInstructor}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ClientesComponent,
    CursosComponent,
    MateriaComponent,
    FormCliente,
    FormAgregarMateria,
    InstructorGetComponent,
    AgregarInstructor
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [ClientesService, MateriaService, InstructorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
