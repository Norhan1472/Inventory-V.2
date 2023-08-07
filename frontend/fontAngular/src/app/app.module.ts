import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrandsComponent } from './components/brands/brands.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UpdateBrandComponent } from './components/update-brand/update-brand.component';
import {  MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule} from '@angular/material/dialog';
import { ExampleDialogComponent } from './components/example-dialog/example-dialog.component';
import { MatCommonModule } from '@angular/material/core';
import { MatFormFieldModule} from '@angular/material/form-field';
import { OrdersComponent } from './components/orders/orders.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { AddOrderComponent } from './components/add-order/add-order.component';
import { ServersComponent } from './components/servers/servers.component';
import { HistoryComponent } from './components/history/history.component';
import { UpdateCategoryComponent } from './components/update-category/update-category.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { DeleteOrderComponent } from './components/delete-order/delete-order.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { UpdateDataEmployeeComponent } from './components/update-data-employee/update-data-employee.component';
import { AddOrderProductComponent } from './components/add-order-product/add-order-product.component';
import { AddServerComponent } from './components/add-server/add-server.component';
import { SystemInfoComponent } from './components/system-info/system-info.component';
import { ServerDetailsComponent } from './components/server-details/server-details.component';
import { AddSystemComponent } from './components/add-system/add-system.component';
import { UpdateSystemComponent } from './components/update-system/update-system.component';
import { AddServerDetailsComponent } from './components/add-server-details/add-server-details.component';
import { ServerDetailsDataComponent } from './components/server-details-data/server-details-data.component';
import { UpdateServerDetailsComponent } from './components/update-server-details/update-server-details.component';
import { AuthenticationComponent } from './components/authentication/authentication.component'
import { HttpInterceptorAuthService } from './services/http-interceptor-auth.service';
@NgModule({
  declarations: [
    AppComponent,
    BrandsComponent,
    CategoriesComponent,
    SideBarComponent,
    DashBoardComponent,
    UpdateBrandComponent,
    ExampleDialogComponent,
    OrdersComponent,
    AddProductComponent,
    AddOrderComponent,
    ServersComponent,
    HistoryComponent,
    UpdateCategoryComponent,
    CategoryDetailsComponent,
    UpdateProductComponent,
    ProductDetailsComponent,
    DeleteOrderComponent,
    OrderDetailsComponent,
    UpdateDataEmployeeComponent,
    AddOrderProductComponent,
    AddServerComponent,
    SystemInfoComponent,
    ServerDetailsComponent,
    AddSystemComponent,
    UpdateSystemComponent,
    AddServerDetailsComponent,
    ServerDetailsDataComponent,
    UpdateServerDetailsComponent,
    AuthenticationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    MatCommonModule,
    MatFormFieldModule
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS,useClass:HttpInterceptorAuthService,multi:true}

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
