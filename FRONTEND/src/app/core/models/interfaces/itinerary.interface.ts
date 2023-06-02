export interface Itinerary {
    province: string;
    town: TownDTO;
    postCode: number;
    roadType: string;
    roadName: string;
    roadNumber: number;
}

interface TownDTO{
    cdmuni:number;
    dsmuni:string;
}