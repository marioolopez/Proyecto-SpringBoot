import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  //url apuntando a antigua ruta BACKEND
  public url : any = 'http://localhost:8080/v1/categorias';

  //url apuntando a AWS (una bbdd en la nube que es de AMAZON WEB SERVICES)
  //public url : any = 'http://apirest-books-aws-env.eba-2gkfvsxj.eu-north-1.elasticbeanstalk.com/v1/categorias';

  constructor(private httpClient: HttpClient) {}

  obtenerCategorias(){
    const header = new HttpHeaders({
      authorization : 'Basic ' + btoa("alfredo" + ':' + "alfredo123")
    });
    return this.httpClient.get(this.url, {headers: header});
  }

}
