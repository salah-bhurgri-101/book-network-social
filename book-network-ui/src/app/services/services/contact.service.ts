/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ContactResponse } from '../models/contact-response';
import { deleteContact } from '../fn/contact/delete-contact';
import { DeleteContact$Params } from '../fn/contact/delete-contact';
import { findAllByOwner } from '../fn/contact/find-all-by-owner';
import { FindAllByOwner$Params } from '../fn/contact/find-all-by-owner';
import { getContactById } from '../fn/contact/get-contact-by-id';
import { GetContactById$Params } from '../fn/contact/get-contact-by-id';
import { PageResponseContactResponse } from '../models/page-response-contact-response';
import { saveContact } from '../fn/contact/save-contact';
import { SaveContact$Params } from '../fn/contact/save-contact';
import { uploadImage } from '../fn/contact/upload-image';
import { UploadImage$Params } from '../fn/contact/upload-image';

@Injectable({ providedIn: 'root' })
export class ContactService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `saveContact()` */
  static readonly SaveContactPath = '/contacts';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveContact()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveContact$Response(params: SaveContact$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveContact(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveContact$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveContact(params: SaveContact$Params, context?: HttpContext): Observable<number> {
    return this.saveContact$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getContactById()` */
  static readonly GetContactByIdPath = '/contacts/{contact-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getContactById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContactById$Response(params: GetContactById$Params, context?: HttpContext): Observable<StrictHttpResponse<ContactResponse>> {
    return getContactById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getContactById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContactById(params: GetContactById$Params, context?: HttpContext): Observable<ContactResponse> {
    return this.getContactById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ContactResponse>): ContactResponse => r.body)
    );
  }

  /** Path part for operation `uploadImage()` */
  static readonly UploadImagePath = '/contacts/{contact-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadImage()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadImage$Response(params: UploadImage$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadImage(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadImage$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadImage(params: UploadImage$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadImage$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `deleteContact()` */
  static readonly DeleteContactPath = '/contacts/{contact-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteContact()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteContact$Response(params: DeleteContact$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return deleteContact(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteContact$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteContact(params: DeleteContact$Params, context?: HttpContext): Observable<number> {
    return this.deleteContact$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findAllByOwner()` */
  static readonly FindAllByOwnerPath = '/contacts/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllByOwner$Response(params?: FindAllByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseContactResponse>> {
    return findAllByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllByOwner(params?: FindAllByOwner$Params, context?: HttpContext): Observable<PageResponseContactResponse> {
    return this.findAllByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseContactResponse>): PageResponseContactResponse => r.body)
    );
  }

}
