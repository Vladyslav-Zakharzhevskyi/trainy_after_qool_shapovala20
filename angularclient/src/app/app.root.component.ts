import {Component} from '@angular/core';
import { Subject } from 'rxjs'
import {SubjectPoolService} from "./subject-pool.service";
import {ContextService} from "./service/context.service";
import {ApiService} from "./api/api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: 'application-main-page.html',
  styleUrls: ['./application-main.page.css']
})
export class AppRootComponent {

  public userIsLoggedIn: boolean = false;

  constructor(private router: Router, private context: ContextService, private subjectPoolService: SubjectPoolService, private api: ApiService) {
    this.userIsLoggedIn = context.userIsLoggedIn();
    this.createAndSubscribeToLoggedInAction();
  }

  private createAndSubscribeToLoggedInAction(): void {
    let loggedInSubject$ = new Subject<boolean>();
    loggedInSubject$.subscribe(loginLogout => {
      console.log("User logged in: " + loginLogout);
      this.userIsLoggedIn = loginLogout;
    });
    this.subjectPoolService.addSubjectToPool(SubjectPoolService.LOGIN_LOGOUT_ACTION, loggedInSubject$);
  }

  public logout(): void {
    this.context.logout();
    this.subjectPoolService.triggerSubject(SubjectPoolService.LOGIN_LOGOUT_ACTION, false);
    this.api.logout().subscribe();
    this.router.navigate(['/login'])
  }


}

