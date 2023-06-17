import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BodyOfWaterComponent } from './components/body-of-water/body-of-water.component';
import { CatchLogComponent } from './components/catch-log/catch-log.component';
import { FishComponent } from './components/fish/fish.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'fish', component: FishComponent },
  { path: 'waters', component: BodyOfWaterComponent },
  { path: 'logs', component: CatchLogComponent },
  { path: 'logs/:id', component: CatchLogComponent },
  { path: '**', component: NotFoundComponent } //page not found route
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
