import {Component} from '@angular/core';
import {SubjectPoolService} from "../service/subjects/subject-pool.service";
import {ContextService} from "../service/context/context.service";
import {ApiService} from "../api/api.service";
import {Router} from "@angular/router";
import {AuthenticationStateService} from "../service/subjects/authentication-state.service";
import {SnackBarService} from "../service/custom/snack-bar.service";

@Component({
  selector: 'application-entry-point',
  templateUrl: 'application-main-page.html',
  styleUrls: ['./application-main.page.css']
})
export class ApplicationEntryPointComponent {

  public userIsLoggedIn: boolean = false;

  constructor(private router: Router,
              private context: ContextService,
              private subjectPoolService: SubjectPoolService,
              private authState: AuthenticationStateService,
              private snackBarService: SnackBarService,
              private api: ApiService) {
    this.init();
    this.initLoginLogout();
  }

  private init() {
    this.userIsLoggedIn = this.context.userIsLoggedIn();
    // Get application settings
    this.getApplicationSettings();
  }

  private getApplicationSettings() {
    this.api.getApplicationSettings().subscribe(applicationSettings => {
      if (applicationSettings != null) {
        this.context.setApplicationSettings(applicationSettings);
      }
    });
  }

  private initLoginLogout(): void {
    this.authState.getStateChange().subscribe(isUserAuthenticated => {
      this.userIsLoggedIn = isUserAuthenticated;

      if (!isUserAuthenticated) {
        this.context.logout();
        this.api.logout().subscribe();
        this.router.navigate(['/login']);
      }
    });
  }

  public logout(): void {
    this.authState.setState(false);
    this.snackBarService.showSnackBar("Logon has been successful!", "error", 4000);
  }


}

