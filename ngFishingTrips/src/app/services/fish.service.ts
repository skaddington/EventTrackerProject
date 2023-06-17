import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CatchLog } from '../models/catch-log';
import { Fish } from '../models/fish';

@Injectable({
  providedIn: 'root'
})
export class FishService {
url: string = environment.baseUrl + 'api/fish';

  constructor(private http: HttpClient) {}

  index(): Observable<Fish[]> {
    return this.http.get<Fish[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('FishService.index(): error retrieving fish: ' + err)
        );
      })
    );
  }

  show(fishId: number): Observable<Fish> {
    return this.http.get<Fish>(this.url + '/' + fishId + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('FishService.index(): error retrieving fish: ' + err)
        );
      })
    );
  }


}
