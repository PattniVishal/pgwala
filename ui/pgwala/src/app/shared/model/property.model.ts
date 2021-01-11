import { AccomodationType } from './accomodation-type.model';
import { Address } from './address.model';
import { Facility } from './facility.model';

export class Property{
    
    id: string;
    sellerName: string;
    sellerEmail: string;
    propertyName: string;
    address: Address;
    accomodationTypes: AccomodationType[];
    facilities: Facility[];
    genders: string[];
    description: string;

}