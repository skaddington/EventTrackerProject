import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CatchLog } from '../models/catch-log';

@Injectable({
   providedIn: 'root',
})
export class CatchLogService {
   url: string = environment.baseUrl + 'api/logs';

   constructor(private http: HttpClient) {}

   index(): Observable<CatchLog[]> {
      return this.http.get<CatchLog[]>(this.url + '?sorted=true').pipe(
         catchError((err: any) => {
            console.log(err);
            return throwError(
               () =>
                  new Error(
                     'CatchLogService.index(): error retrieving logs: ' + err
                  )
            );
         })
      );
   }

   show(logId: number): Observable<CatchLog> {
      return this.http.get<CatchLog>(this.url + '/' + logId).pipe(
         catchError((err: any) => {
            console.log(err);
            return throwError(
               () =>
                  new Error(
                     'CatchLogService.index(): error retrieving log: ' + err
                  )
            );
         })
      );
   }

   create(newLog: CatchLog): Observable<CatchLog> {
      console.log(newLog);
      return this.http.post<CatchLog>(this.url, newLog).pipe(
         catchError((err: any) => {
            console.error(err);
            return throwError(
               () =>
                  new Error(
                     'CatchLogService.create(): error creating  log: ' + err
                  )
            );
         })
      );
   }

   update(log: CatchLog): Observable<CatchLog> {
      return this.http.put<CatchLog>(this.url + '/' + log.id, log).pipe(
         catchError((err: any) => {
            console.error(err);
            return throwError(
               () =>
                  new Error('CatchLogService.update(): error updating log: ' + err)
            );
         })
      );
   }

   destroy(logId: number): Observable<void> {
      return this.http.delete<void>(this.url + '/' + logId).pipe(
         catchError((err: any) => {
            console.error(err);
            return throwError(
               () =>
                  new Error(
                     'CatchLogService.destroy(): error deleting log: ' + err
                  )
            );
         })
      );
   }
}
