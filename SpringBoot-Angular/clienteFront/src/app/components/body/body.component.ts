import { CommonModule } from '@angular/common';
import { CategoriasService } from './../../services/categorias.service';
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-body',
  imports: [CommonModule],
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class BodyComponent implements OnInit{

  public categorias: any;
  public arrCategorias: any;

  constructor(private _categorias:CategoriasService){}

  ngOnInit(): void {
    this.obtenerCategorias();
  }

  obtenerCategorias(){
    this._categorias.obtenerCategorias().subscribe((res: any) => {
        this.categorias = res;
        this.arrCategorias = this.categorias.categoriaResponse.categoria; //recuperando el array de categorias
        console.log(this.categorias);
    });
  }

}
