import axios, {AxiosRequestConfig, Method} from 'axios'
import {API_PATH} from './config';

export type Headers = {[key: string]: string};

export class HttpService {

    public get(uri: string, config?: AxiosRequestConfig) {
        return this.request(uri, "GET", undefined, config);
    }

    public post(uri: string, body: any, config?: AxiosRequestConfig) {
        return this.request(uri, "POST", body, config);
    }

    public put(uri: string, body: any, config?: AxiosRequestConfig) {
        return this.request(uri, "POST", body, config);
    }

    public delete(uri: string, config?: AxiosRequestConfig) {
        return this.request(uri, "DELETE", undefined, config);
    }

    private request(uri: string, method: Method, body: any, config: AxiosRequestConfig = {}) {
        return axios.request(Object.assign(config, {
            baseURL: API_PATH,
            url: uri,
            method: method,
            data: body,
        }))
    }

}
