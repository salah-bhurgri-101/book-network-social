import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// import { NgIf , NgFor } from '@angular/common';
// import { NgModule } from '@angular/core';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'book-network-ui';
}
