import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Recambio } from './recambio.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RecambioService {

    private resourceUrl = SERVER_API_URL + 'api/recambios';

    constructor(private http: Http) { }

    create(recambio: Recambio): Observable<Recambio> {
        const copy = this.convert(recambio);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(recambio: Recambio): Observable<Recambio> {
        const copy = this.convert(recambio);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: string): Observable<Recambio> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Recambio.
     */
    private convertItemFromServer(json: any): Recambio {
        const entity: Recambio = Object.assign(new Recambio(), json);
        return entity;
    }

    /**
     * Convert a Recambio to a JSON which can be sent to the server.
     */
    private convert(recambio: Recambio): Recambio {
        const copy: Recambio = Object.assign({}, recambio);
        return copy;
    }
}
