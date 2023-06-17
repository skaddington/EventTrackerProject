import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BodyOfWater } from 'src/app/models/body-of-water';
import { BodyOfWaterService } from 'src/app/services/body-of-water.service';

@Component({
  selector: 'app-body-of-water',
  templateUrl: './body-of-water.component.html',
  styleUrls: ['./body-of-water.component.css'],
})
export class BodyOfWaterComponent implements OnInit {
  waters: BodyOfWater[] = [];

  newWater: BodyOfWater = new BodyOfWater();
  editWater: BodyOfWater | null = null;
  selectedWater: BodyOfWater | null = null;
  waterType: string = '';

  constructor(
    private waterService: BodyOfWaterService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAllWaters();
  }

  displayWaterTable() {
    return (this.selectedWater = null);
  }

  loadAllWaters(): void {
    this.waterService.index().subscribe({
      next: (waterList) => {
        this.waters = waterList;
      },
      error: (problem) => {
        console.error(
          'BodyOfWaterComponent.loadAllWaters(); error loading bodies of water'
        );
        console.error(problem);
      },
    });
  }

  showWater(waterId: number): void {
    this.waterService.show(waterId).subscribe({
      next: (foundWater) => {
        if (foundWater.type){
          this.waterType = "Stream/River";
        } else {
          this.waterType = "Lake/Reservoir";
        }
        this.selectedWater = foundWater;
        this.loadAllWaters();
      },
      error: (problem) => {
        console.error('WaterComponent.showWater(): error getting water:');
        console.error(problem);
        this.router.navigateByUrl('still a loser');
      },
    });
  }
}
