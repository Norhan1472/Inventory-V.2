import { Component, OnInit } from '@angular/core';
import { History } from 'src/app/interfaces/history';
import  jspdf from 'jspdf';
import * as jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import 'jspdf-autotable';
import { HistoryService } from 'src/app/services/history.service';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit{
  histories : History[] = [];

  constructor(private historyService:HistoryService){}

  ngOnInit(): void {
    this.getAllHistories();
  }
  getAllHistories(){
    this.historyService.getAllHistories().subscribe(
      data=>{
        this.histories = data;
      }
    )
  }
  header =[['empName','productName','serialNumber',
  'brand','description','dateReceived','dateRetrieved','reason']];

  data = this.histories.map((history) => {

      history.empName,
      history.productName,
      history.serialNumber,
      history.brand,
      history.description,
      history.dateReceived,
      history.dateRetrieved,
      history.reason
  });

  generatePdf(){

const doc = new jsPDF.default();
doc.setFontSize(20);
doc.text("PDF file in Angular",11,8);
    (doc as any).autoTable({head: this.header, body: [this.histories],theme:'striped'});
    doc.output('dataurlnewwindow');
    doc.save('data.pdf');
}
  downloadPdf(){
    this.historyService.generatePdf().subscribe(
      data=>{
        console.log(data);
      }
    )
  }
  filterData(value:string){
    console.log(value);
    this.historyService.findByAnyData(value).subscribe(
      data=>{
        console.log(data);
        this.histories=data;
      },
      error=>{
        console.log(error);
      }
    )
  }
}
