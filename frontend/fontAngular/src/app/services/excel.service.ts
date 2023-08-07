import { Injectable } from '@angular/core';
import { Workbook } from 'exceljs';
import * as fs from 'file-saver';
import { Product } from '../interfaces/product';
@Injectable({
  providedIn: 'root'
})
export class ExcelService {
  constructor() { }

generateExcel(data:Product[]){
  console.log("here");
  console.log(data);
  /*const title ="List of Products";
  const header = ["Product Serial Number","Model",
                  "Product Name","Specification",
                  "Status","Type",
                "category","brand"];*/
  const title = 'List of Products';
  const header = [
    'Product Serial Number',
    'Model',
    'Product Name',
    'Specification',
    'Status',
    'Type',
    'Category',
    'Brand'
  ];
  let workBook = new Workbook();
  // work sheet name
  let workSheet = workBook.addWorksheet("Product Data");
  let headerRow = workSheet.addRow(header);
  headerRow.eachCell((cell , number)=>{
    cell.fill = {
      type:'pattern',
      pattern:'solid',
      fgColor :{argb:'FFFFFF00'},
      bgColor:{argb:'FF0000FF'}
    }
    cell.border = {
      top:{style:'thin'},
      left:{style:'thin'},
      bottom:{style:'thin'},
      right:{style:'thin'}
    }
  });

  const rows = data.map((product) => {
    return [
      product.serialNumber,
      product.model,
      product.productName,
      product.specification,
      product.status,
      product.type,
      product.category.categoryName,
      product.brand.brandName
    ];
  });

  workSheet.addRows(rows);
  workBook.xlsx.writeBuffer().then((data)=>{

    let bolb = new Blob([data],{type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
    console.log(bolb);
    fs.saveAs(bolb,'ProductData.xlsx');
  })
}

}
