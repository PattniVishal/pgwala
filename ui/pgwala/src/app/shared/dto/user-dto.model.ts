import { Property } from '../model/property.model';

export class UserDTO{

    name: string;
    email: string;
    password: string;
    role: string;
    aadharNumber?: string;
    properties?: Property[];

}