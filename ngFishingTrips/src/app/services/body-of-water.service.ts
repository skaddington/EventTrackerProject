import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CatchLog } from '../models/catch-log';
import { BodyOfWater } from '../models/body-of-water';

@Injectable({
  providedIn: 'root'
})
export class BodyOfWaterService {
url: string = environment.baseUrl + 'api/waters';

  constructor(private http: HttpClient) {}

  index(): Observable<BodyOfWater[]> {
    return this.http.get<BodyOfWater[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('BodyOfWaterService.index(): error retrieving bodies of water: ' + err)
        );
      })
    );
  }

  show(waterId: number): Observable<BodyOfWater> {
    return this.http.get<BodyOfWater>(this.url + '/' + waterId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BodyOfWaterService.index(): error retrieving water: ' + err)
        );
      })
    );
  }


}
