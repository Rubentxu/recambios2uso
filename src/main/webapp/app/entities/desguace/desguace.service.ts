import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Desguace } from './desguace.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DesguaceService {

    private resourceUrl = SERVER_API_URL + 'api/desguaces';

    constructor(private http: Http) { }

    create(desguace: Desguace): Observable<Desguace> {
        const copy = this.convert(desguace);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(desguace: Desguace): Observable<Desguace> {
        const copy = this.convert(desguace);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: string): Observable<Desguace> {
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
     * Convert a returned JSON object to Desguace.
     */
    private convertItemFromServer(json: any): Desguace {
        const entity: Desguace = Object.assign(new Desguace(), json);
        return entity;
    }

    /**
     * Convert a Desguace to a JSON which can be sent to the server.
     */
    private convert(desguace: Desguace): Desguace {
        const copy: Desguace = Object.assign({}, desguace);
        return copy;
    }
}
