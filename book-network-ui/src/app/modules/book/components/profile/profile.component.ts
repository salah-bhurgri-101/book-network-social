import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/services/models';
import { UserService } from 'src/app/services/services';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent {

user: User = {};

  constructor(
    private userService: UserService,
    // private route: Router
  ) {
  }
    ngOnInit(): void {
      this.getUser();
      // const linkColor = document.querySelectorAll('.nav-link');
      // linkColor.forEach(link => {
      //   if (window.location.href.endsWith(link.getAttribute('href') || '')) {
      //     link.classList.add('active');
      //   }
      //   link.addEventListener('click', () => {
      //     linkColor.forEach(l => l.classList.remove('active'));
      //     link.classList.add('active');
      //   });
      // });
    }

    getUser(){
      this.userService.getUser()
      .subscribe({
        next:(u)=>{
          this.user = u;
        }
      });
    }




}
