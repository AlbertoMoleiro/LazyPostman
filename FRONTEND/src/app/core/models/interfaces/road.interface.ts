export interface Road {
    province: string;
    town: string;
    postCode: number;
    roadType: string;
    roadName: string;
    minOdd: number | null;
    maxOdd: number | null;
    minEven: number | null;
    maxEven: number | null;
}