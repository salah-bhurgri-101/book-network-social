// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-menu',
//   templateUrl: './menu.component.html',
//   styleUrls: ['./menu.component.scss']
// })
// export class MenuComponent {

// }

import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/services/models';
import { UserService } from 'src/app/services/services';
// import {KeycloakService} from '../../../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  user: User = {};

  constructor(
    private userService: UserService,
    private route: Router
  ) {
  }
    ngOnInit(): void {
      this.getUser();
      const linkColor = document.querySelectorAll('.nav-link');
      linkColor.forEach(link => {
        if (window.location.href.endsWith(link.getAttribute('href') || '')) {
          link.classList.add('active');
        }
        link.addEventListener('click', () => {
          linkColor.forEach(l => l.classList.remove('active'));
          link.classList.add('active');
        });
      });
    }

    getUser(){
      this.userService.getUser()
      .subscribe({
        next:(u)=>{
          this.user = u;
        }
      });
    }

    profile(){
      this.route.navigate(['profile']);
    }
  async logout() {
    // await this.keycloakService.logout();
    localStorage.removeItem('token');
    window.location.reload();
  }
}

