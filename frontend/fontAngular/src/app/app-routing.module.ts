import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrandsComponent } from './components/brands/brands.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { OrdersComponent } from './components/orders/orders.component';
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
import { AuthenticationComponent } from './components/authentication/authentication.component';
import { LogInActivateService } from './services/servicesAuthentication/activate/log-in-activate.service';
import { RouteActivateService } from './services/servicesAuthentication/activate/route-activate.service';

const routes: Routes = [
  {path:'signIn', component:AuthenticationComponent,canActivate:[LogInActivateService]},
  {path:"updateServerDetails/:id",component:UpdateServerDetailsComponent,canActivate:[RouteActivateService]},
  {path:"serverDataDetails/:id",component:ServerDetailsDataComponent,canActivate:[RouteActivateService]},
  {path:"addServerDetails",component:AddServerDetailsComponent,canActivate:[RouteActivateService]},
  {path:"updateSystem/:id",component:UpdateSystemComponent,canActivate:[RouteActivateService]},
  {path:"addSystem",component:AddSystemComponent,canActivate:[RouteActivateService]},
  {path:"system",component:SystemInfoComponent,canActivate:[RouteActivateService]},
  {path:"serverDetails",component:ServerDetailsComponent,canActivate:[RouteActivateService]},
  {path:"addServer",component:AddServerComponent,canActivate:[RouteActivateService]},
  {path:"addOrderProduct/:id",component:AddOrderProductComponent,canActivate:[RouteActivateService]},
  {path:"updateDataEmployee/:id",component:UpdateDataEmployeeComponent,canActivate:[RouteActivateService]},
  {path:"orderDetails/:id",component:OrderDetailsComponent,canActivate:[RouteActivateService]},
  {path:"deleteOrder/:id",component:DeleteOrderComponent,canActivate:[RouteActivateService]},
  {path:"productDetails/:id",component:ProductDetailsComponent,canActivate:[RouteActivateService]},
  {path:"updateProduct/:id",component:UpdateProductComponent,canActivate:[RouteActivateService]},
  {path:'getCategoryById/:id',component:CategoryDetailsComponent,canActivate:[RouteActivateService]},
  {path:"updateCategory/:id",component:UpdateCategoryComponent,canActivate:[RouteActivateService]},
  {path:"dashBoard",component:DashBoardComponent,canActivate:[RouteActivateService]},
  {path:"brand",component:BrandsComponent,canActivate:[RouteActivateService]},
  {path:"category",component:CategoriesComponent,canActivate:[RouteActivateService]},
  {path:"orders",component:OrdersComponent,canActivate:[RouteActivateService]},
  {path:"server",component:ServersComponent,canActivate:[RouteActivateService]},
  {path:"history",component:HistoryComponent,canActivate:[RouteActivateService]},
  {path:"addProduct",component:AddProductComponent,canActivate:[RouteActivateService]},
  {path:"addOrder",component:AddOrderComponent,canActivate:[RouteActivateService]},
  {path:'',redirectTo:'signIn',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
