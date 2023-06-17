import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Fish } from 'src/app/models/fish';
import { FishService } from 'src/app/services/fish.service';

@Component({
  selector: 'app-fish',
  templateUrl: './fish.component.html',
  styleUrls: ['./fish.component.css'],
})
export class FishComponent implements OnInit {
  fishies: Fish[] = [];

  newFish: Fish = new Fish();
  editFish: Fish | null = null;
  selectedFish: Fish | null = null;

  constructor(
    private fishService: FishService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadFishies();
  }

  displayFishTable() {
    return (this.selectedFish = null);
  }

  loadFishies(): void {
    this.fishService.index().subscribe({
      next: (allTheFishies) => {
        this.fishies = allTheFishies;
      },
      error: (problem) => {
        console.error('FishComponent.loadAllFishies(): error loading fishies');
        console.error(problem);
      },
    });
  }

  showFish(fishId: number): void {
    this.fishService.show(fishId).subscribe({
      next: (foundFish) => {
        this.selectedFish = foundFish;
        this.loadFishies();
      },
      error: (problem) => {
        console.error('FishComponent.showFish(): error getting fish:');
        console.error(problem);
        this.router.navigateByUrl('still a loser');
      },
    });
  }
}
