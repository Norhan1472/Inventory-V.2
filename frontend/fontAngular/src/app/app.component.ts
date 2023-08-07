import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ExampleDialogComponent } from './components/example-dialog/example-dialog.component';
import { UpdateBrandComponent } from './components/update-brand/update-brand.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'fontAngular';
  animal!: string;
  name!: string;

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    let dialogRef = this.dialog.open(UpdateBrandComponent, {

      height: '350px',
        width: '250px',

        position: {   top: 'center',
        left: 'center'
       }
      }
    );

    dialogRef.afterClosed().subscribe(result => {
      this.animal = result;
    });
  }
}
