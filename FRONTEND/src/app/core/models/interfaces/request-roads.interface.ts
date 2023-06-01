import { Road } from "./road.interface";

export interface RequestRoads {
    routeName: string;
    roads: Road[];
}