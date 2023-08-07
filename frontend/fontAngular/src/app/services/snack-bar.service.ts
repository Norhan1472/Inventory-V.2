import { MatSnackBar } from '@angular/material/snack-bar';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SnackBarService {

  constructor(private snackBar:MatSnackBar) { }

  openSnackBar(message:string,action:string){
    if(action==='error'){
      this.snackBar.open(
        message,
        '',
        {
          horizontalPosition:'center',
          verticalPosition:'top',
          duration:200,
          panelClass:[
            'black-snackBar'
          ]
        }
      )
    }
    else{
      this.snackBar.open(
        message,
        '',
        {
          horizontalPosition:'center',
          verticalPosition:'top',
          duration:200,
          panelClass:[
            'green-snackBar'
          ]
        }
      )
    }
  }
}
