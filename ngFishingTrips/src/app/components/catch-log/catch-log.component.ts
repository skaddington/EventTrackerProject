import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BodyOfWater } from 'src/app/models/body-of-water';
import { CatchLog } from 'src/app/models/catch-log';
import { Fish } from 'src/app/models/fish';
import { TimeOfDay } from 'src/app/models/time-of-day';
import { CatchLogService } from 'src/app/services/catch-log.service';

@Component({
  selector: 'app-catch-log',
  templateUrl: './catch-log.component.html',
  styleUrls: ['./catch-log.component.css'],
})
export class CatchLogComponent implements OnInit {
  catchlogs: CatchLog[] = [];

  newLog: CatchLog = new CatchLog();
  editLog: CatchLog | null = null;
  selectedLog: CatchLog | null = null;

  constructor(
    private logService: CatchLogService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.newLog.water = new BodyOfWater();
    this.newLog.fish = new Fish();
    this.newLog.time = new TimeOfDay();

}

  ngOnInit(): void {
    this.loadCatchLogs();
  }

  displayLogTable() {
    return (this.selectedLog = null);
  }

  loadCatchLogs(): void {
    this.logService.index().subscribe({
      next: (allTheLogs) => {
        this.catchlogs = allTheLogs;
      },
      error: (noFun) => {
        console.error('CatchLogComponent.loadCatchLog(): error loading Logs');
        console.error(noFun);
      },
    });
  }

  showLog(logId: number): void {
    this.logService.show(logId).subscribe({
      next: (foundLog) => {
        this.selectedLog = foundLog;
        this.loadCatchLogs();
      },
      error: (problem) => {
        console.error('CatchLogComponent.showLog(): error getting log:');
        console.error(problem);
        this.router.navigateByUrl('still a loser');
      },
    });
  }

  addLog(newLog: CatchLog): void {
    this.logService.create(newLog).subscribe({
      next: (createdLog) => {
        // this.selectedLog = createdLog;
        // this.newLog = new CatchLog();
        this.loadCatchLogs();
      },
      error: (problem) => {
        console.error('CatchLogComponent.addLog(): error creating log:');
        console.error(problem);
      },
    });
  }

  deleteLog(logId: number): void {
    this.logService.destroy(logId).subscribe({
      next: () =>{
        this.loadCatchLogs();
        this.selectedLog = null;
      },
      error: (problem) => {
        console.error('CatchLogComponent.deleteLog(): error deleting log:');
        console.log(problem);
      }
    });
    this.loadCatchLogs();
  }


}
