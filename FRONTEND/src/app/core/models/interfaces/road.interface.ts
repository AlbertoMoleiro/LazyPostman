import { Town } from "./town.interface";

export interface Road {
    province: string;
    town: Town;
    postCode: number;
    roadType: string;
    roadName: string;
    minOdd: number | null;
    maxOdd: number | null;
    minEven: number | null;
    maxEven: number | null;
}