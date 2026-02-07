import { Component } from '@angular/core';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { BodyComponent } from "./components/body/body.component";

@Component({
  selector: 'app-root',
  imports: [NavbarComponent, BodyComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'clienteFront';
}
