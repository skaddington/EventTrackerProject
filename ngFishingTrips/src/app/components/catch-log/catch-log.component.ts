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

   ngOnInit() {
      let idString = this.route.snapshot.paramMap.get('id');
      if (!this.selectedLog && idString) {
         console.log(idString);
         let logId: number = Number.parseInt(idString);
         console.log(logId);
         if (isNaN(logId)) {
            this.router.navigateByUrl('loser');
         } else {
            this.showLog(logId);
         }
      }
      this.reload();
   }

   displayLogTable() {
      return (this.selectedLog = null);
   }

   displayLogDetails(log: CatchLog) {
      return (this.selectedLog = log);
   }

   reload(): void {
      this.logService.index().subscribe({
         next: (allTheLogs) => {
            this.catchlogs = allTheLogs;
         },
         error: (noFun) => {
            console.error(
               'CatchLogComponent.loadCatchLog(): error loading Logs'
            );
            console.error(noFun);
         },
      });
   }

   showLog(logId: number): void {
      this.logService.show(logId).subscribe({
         next: (foundLog) => {
            this.selectedLog = foundLog;
            this.reload();
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
            this.selectedLog = createdLog;
            // this.newLog = new CatchLog();
            this.reload();
         },
         error: (problem) => {
            console.error('CatchLogComponent.addLog(): error creating log:');
            console.error(problem);
         },
      });
   }

   setEditLog(): void {
      this.editLog = Object.assign({}, this.selectedLog);
   }

   updateLog(log: CatchLog, goToDetails: boolean = true): void {
      this.logService.update(log).subscribe({
         next: (updatedLog) => {
            if (goToDetails) {
               this.selectedLog = updatedLog;
            }
            this.editLog = null;
            this.reload();
         },
         error: (noJoy) => {
            console.error('CatchLogComponent.updateLog(): error on update');
            console.error(noJoy);
         },
      });
   }

   deleteLog(logId: number): void {
      this.logService.destroy(logId).subscribe({
         next: () => {
            this.reload();
            this.selectedLog = null;
         },
         error: (problem) => {
            console.error('CatchLogComponent.deleteLog(): error deleting log:');
            console.log(problem);
         },
      });
      this.reload();
   }
}
