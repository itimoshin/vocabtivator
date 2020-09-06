import {HttpService} from "../HttpService";
import {VocabTableDTO} from "../model/models";
import {AxiosResponse} from "axios";

export class VocabService {

    private readonly httpService = new HttpService();

    public uploadXls(file: File): Promise<VocabTableDTO> {
        const data = new FormData();
        data.append('file', file);
        return this.httpService.post('/vocab-service/vocab-table/xls', data).then((resp: AxiosResponse<VocabTableDTO>) => resp.data);
    }

    public uploadGoogleSpreadsheet(fileId: string, oauthToken: string): Promise<VocabTableDTO> {
        return this.httpService.post('/vocab-service/vocab-table/google', {
            fileId: fileId,
            oauthToken: oauthToken
        }).then((resp: AxiosResponse<VocabTableDTO>) => resp.data);
    }

}
