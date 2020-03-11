import { NgModule } from "@angular/core";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//Angular Material Components
import { MatButtonModule } from "@angular/material/button";

const allMatFeatures = [
  BrowserAnimationsModule,
  MatButtonModule,
];

@NgModule({
  imports: [allMatFeatures],
  exports: [allMatFeatures]
})
export class AppMaterialModule {}
