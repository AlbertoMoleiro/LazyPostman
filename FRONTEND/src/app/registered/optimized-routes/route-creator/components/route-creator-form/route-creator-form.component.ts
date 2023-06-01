import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Observable, map, startWith, takeUntil } from 'rxjs';
import { autocompleteObjectValidator } from 'src/app/core/models/validators/autocompleteObject.validator';
import { autocompleteNumberValidator } from 'src/app/core/models/validators/autocompleteNumber.validator';
import { roadNumberValidator } from '../roadNumber.validator';
import { oddEvenChecked } from '../odd-even-checked.validator';
import { RouteCreatorService } from 'src/app/core/services/route-creator.service';
import { Road } from 'src/app/core/models/interfaces/road.interface';
import { Town } from 'src/app/core/models/interfaces/town.interface';
@Component({
    selector: 'app-route-creator-form',
    templateUrl: './route-creator-form.component.html',
    styleUrls: ['./route-creator-form.component.css']
})
export class RouteCreatorFormComponent {
    routeCreateForm: FormGroup = new FormGroup({});

    //TODO: Get this from the backend
    // towns: any[] = [{ name: "Las Rozas", postCode: [28001, 28067, 12345, 28282, 29292, 27272, 26262, 25252, 24242, 23232, 21212, 21322, 21422, 21522] }, { name: "Guadarrama", postCode: [28440] }, { name: "Gargantilla del Lozoya y Pinilla de Buitrago", postCode: [28200] }];
    towns: Town[] = [];
    filteredTowns: Observable<any[]> = new Observable<any[]>();

    roads: any[] = [{ name: "falsa", minOdd: 5, maxOdd: 123, minEven: 4, maxEven: 124 }, { name: "Real", minOdd: 1, maxOdd: 7, minEven: 2, maxEven: 22 }];
    filteredRoads: Observable<any[]> = new Observable<any[]>();

    postCodes: number[] = [];
    filteredPostCodes: Observable<number[]> = new Observable<number[]>();

    roadTypes: string[] = [
        "Acces","Arry", "Avda", "Avia", "Barro", "Branc", "Bulev", "Calle", "Campa", "Caser", "Cjto", "Cllja", "Cllon", "Cmno", "Col", "Compj", "Cra", "Crril", "Cstan", "Ctra", "Custa","Cсada", "Disem","Escal", "Estac","Extrr", "Finca","Gale", "Grup", "Gta", "Jdin", "Lugar", "Parti", "Paseo", "Pbdo", "Pista", "Plaza", "Plzla", "Pnte", "Polig", "Pque",  "Praje", "Prol", "Psaje", "Puert", "Pzo", "Rcda", "Rcon", "Ronda", "Rtda", "Sbida", "Sect", "Senda", "Sitio", "Trva", "Urb", "Via", "Vreda","Zona"
    ];

    //Regex for odd numbers
    oddRegex: RegExp = /^[0-9]*[13579]$/;
    //Regex for even numbers
    evenRegex: RegExp = /^[0-9]*[02468]$/;

    //Clean subscriptions
    private onDestroy = new Subject<void>();

    constructor(private formBuilder: FormBuilder, private routeCreatorService: RouteCreatorService) {
        this.initForm();
    }

    ngOnInit() {

        this.subscribeToFormChanges();

        //TODO: Get this from the backend
        this.routeCreatorService.getTowns()
        .pipe(takeUntil(this.onDestroy))
        .subscribe((towns: Town[]) => {
            this.towns = towns;
            this.filteredTowns = this.initAutocompleteFilter('town', this.townFilter);
            this.initAutocompleteFilters();
        });
    }
    ngOnDestroy() {
        this.onDestroy.next();
        this.onDestroy.complete();
    }

    //Initialization Form
    private initForm() {
        this.routeCreateForm = this.formBuilder.group({
            province: [{ value: 'Madrid', disabled: true }, Validators.required],
            town: ['', autocompleteObjectValidator()],
            postCode: ['', autocompleteNumberValidator(this.postCodes)],
            roadType: [{ value: '', disabled: true }, Validators.required],
            roadName: [{ value: '', disabled: true }, [autocompleteObjectValidator(), Validators.required]],
            allRoad: [{ value: true, disabled: true }],
            odd: [{ value: true, disabled: true }],
            roadNumberMinOdd: [{ value: '', disabled: true }, [Validators.required, Validators.pattern(this.oddRegex)]],
            roadNumberMaxOdd: [{ value: '', disabled: true }, [Validators.required, Validators.pattern(this.oddRegex)]],
            even: [{ value: true, disabled: true }],
            roadNumberMinEven: [{ value: '', disabled: true }, [Validators.required, Validators.pattern(this.evenRegex)]],
            roadNumberMaxEven: [{ value: '', disabled: true }, [Validators.required, Validators.pattern(this.evenRegex)]],
        }, { validators: [roadNumberValidator, oddEvenChecked] });
    }

    //Autocomplete options filters
    private initAutocompleteFilters() {
        this.filteredTowns = this.initAutocompleteFilter('town', this.townFilter);

        this.filteredRoads = this.initAutocompleteFilter('roadName', this.roadFilter);
        this.filteredPostCodes = this.initAutocompleteFilter('postCode', this.postCodeFilter);
    }

    private initAutocompleteFilter(field: string, filtro: (value: any) => any[]): Observable<any[]> {
        return this.routeCreateForm.get(field)!.valueChanges.pipe(
            takeUntil(this.onDestroy),
            startWith(''),
            map(value => filtro(value || '')),
        );
    }

    private townFilter = (value: string | any): Town[] => {
        let filterValue = '';
        if (typeof value !== 'string') {
            filterValue = value.name.toLowerCase();
        } else {

            filterValue = value.toLowerCase();
        }
        return this.towns.filter(town => town.name.toLowerCase().includes(filterValue));
    }

    private roadFilter = (value: string | any): string[] => {
        let filterValue = '';
        if (typeof value !== 'string') {
            filterValue = value.name.toLowerCase();
        } else {
            filterValue = value.toLowerCase();

        }
        return this.roads.filter(road => road.name.toLowerCase().includes(filterValue));
    }

    private postCodeFilter = (value: number): number[] => {
        const filterValue = value.toString();
        return this.postCodes.filter(postCode => postCode.toString().includes(filterValue));
    }
    //Autocomplete display functions, to show the name of the object
    public displayNameFn(object?: any): string {
        return object ? object.name : undefined;
    }

    //Subscriptions

    private subscribeToFormChanges() {
        this.subscribeToTownChanges();
        this.subscribeToPostCodeChanges();
        this.subscribeToRoadTypeChanges();
        this.subscribeToRoadNameChanges();
        this.subscribeToAllRoadChanges();
        this.subscribeToOddChanges();
        this.subscribeToEvenChanges();
    }

    private subscribeToTownChanges() {
        this.routeCreateForm.get('town')?.valueChanges
            .pipe(takeUntil(this.onDestroy))
            .subscribe(town => {
                //Reset postCode and set validator with
                const postCodes = town?.postCode || [];
                this.postCodes = postCodes;
                this.routeCreateForm.get('postCode')?.setValue('');
                this.routeCreateForm.get('postCode')?.setValidators(autocompleteNumberValidator(this.postCodes));

                if (postCodes.length > 1) {
                    this.routeCreateForm.get('postCode')?.enable();
                } else {
                    this.routeCreateForm.get('postCode')?.setValue(postCodes[0]);
                    this.routeCreateForm.get('postCode')?.disable();
                }

            });
    }

    private subscribeToPostCodeChanges() {
        this.routeCreateForm.get('postCode')!.valueChanges
            .pipe(takeUntil(this.onDestroy))
            .subscribe((value) => {
                if (value) {
                    this.routeCreateForm.get('roadType')!.enable();
                } else {
                    this.routeCreateForm.get('roadType')!.disable();
                }
            });
    }

    private subscribeToRoadTypeChanges() {
        this.routeCreateForm.get('roadType')!.valueChanges
            .pipe(takeUntil(this.onDestroy))
            .subscribe((value) => {
                if (value) {
                    this.routeCreatorService.getRoadNames(this.routeCreateForm.get('town')?.value.cdmuni)
                    .pipe(takeUntil(this.onDestroy))
                    .subscribe((roads: Road[]) => {
                        this.roads = roads;
                        this.filteredRoads = this.initAutocompleteFilter('roadName', this.roadFilter);
                        this.routeCreateForm.get('roadName')!.enable();
                    });
                } else {
                    this.routeCreateForm.get('roadName')!.disable();
                }
            });
    }

    private subscribeToRoadNameChanges() {
        this.routeCreateForm.get('roadName')!.valueChanges
            .pipe(takeUntil(this.onDestroy))
            .subscribe((road) => {
                if (road) {
                    //Set the road number fields validators
                    this.routeCreateForm.get('roadNumberMinOdd')!.setValidators([Validators.required, Validators.pattern(this.oddRegex), Validators.min(road.minOdd), Validators.max(road.maxOdd)]);
                    this.routeCreateForm.get('roadNumberMaxOdd')!.setValidators([Validators.required, Validators.pattern(this.oddRegex), Validators.min(road.minOdd), Validators.max(road.maxOdd)]);
                    this.routeCreateForm.get('roadNumberMinEven')!.setValidators([Validators.required, Validators.pattern(this.evenRegex), Validators.min(road.minEven), Validators.max(road.maxEven)]);
                    this.routeCreateForm.get('roadNumberMaxEven')!.setValidators([Validators.required, Validators.pattern(this.evenRegex), Validators.min(road.minEven), Validators.max(road.maxEven)]);

                    //Set the road number fields values
                    this.routeCreateForm.get('roadNumberMinOdd')!.setValue(road.minOdd);
                    this.routeCreateForm.get('roadNumberMaxOdd')!.setValue(road.maxOdd);
                    this.routeCreateForm.get('roadNumberMinEven')!.setValue(road.minEven);
                    this.routeCreateForm.get('roadNumberMaxEven')!.setValue(road.maxEven);

                    //Enable allRoad field
                    this.routeCreateForm.get('allRoad')!.enable();
                }

            });
    }

    private subscribeToAllRoadChanges() {
        this.routeCreateForm.get('allRoad')!.valueChanges
            .pipe(takeUntil(this.onDestroy))
            .subscribe((value) => {

                if (value) {
                    this.routeCreateForm.get('roadNumberMinOdd')!.setValue(this.routeCreateForm.get('roadName')!.value.minOdd);
                    this.routeCreateForm.get('roadNumberMaxOdd')!.setValue(this.routeCreateForm.get('roadName')!.value.maxOdd);
                    this.routeCreateForm.get('roadNumberMinEven')!.setValue(this.routeCreateForm.get('roadName')!.value.minEven);
                    this.routeCreateForm.get('roadNumberMaxEven')!.setValue(this.routeCreateForm.get('roadName')!.value.maxEven);
                    this.routeCreateForm.get('roadNumberMinOdd')!.disable();
                    this.routeCreateForm.get('roadNumberMaxOdd')!.disable();
                    this.routeCreateForm.get('roadNumberMinEven')!.disable();
                    this.routeCreateForm.get('roadNumberMaxEven')!.disable();
                    this.routeCreateForm.get('odd')!.setValue(true);
                    this.routeCreateForm.get('even')!.setValue(true);
                    this.routeCreateForm.get('odd')!.disable();
                    this.routeCreateForm.get('even')!.disable();
                } else if (this.routeCreateForm.get('roadName')!.value) {
                    this.routeCreateForm.get('roadNumberMinOdd')!.enable();
                    this.routeCreateForm.get('roadNumberMaxOdd')!.enable();
                    this.routeCreateForm.get('roadNumberMinEven')!.enable();
                    this.routeCreateForm.get('roadNumberMaxEven')!.enable();
                    this.routeCreateForm.get('odd')!.enable();
                    this.routeCreateForm.get('even')!.enable();
                }

            }
            );
    }

    private subscribeToOddChanges() {
        this.routeCreateForm.get('odd')?.valueChanges.pipe(takeUntil(this.onDestroy)).subscribe(oddChecked => {
            if (!this.routeCreateForm.get('allRoad')!.value) {
                if (oddChecked) {
                    this.routeCreateForm.get('roadNumberMinOdd')?.enable();
                    this.routeCreateForm.get('roadNumberMaxOdd')?.enable();
                    this.routeCreateForm.get('roadNumberMinOdd')!.setValue(this.routeCreateForm.get('roadName')!.value.minOdd);
                    this.routeCreateForm.get('roadNumberMaxOdd')!.setValue(this.routeCreateForm.get('roadName')!.value.maxOdd);
                } else {
                    this.routeCreateForm.get('roadNumberMinOdd')?.disable();
                    this.routeCreateForm.get('roadNumberMaxOdd')?.disable();
                    this.routeCreateForm.get('roadNumberMinOdd')!.setValue(null);
                    this.routeCreateForm.get('roadNumberMaxOdd')!.setValue(null);
                }
            }
        });
    }

    private subscribeToEvenChanges() {
        this.routeCreateForm.get('even')?.valueChanges.pipe(takeUntil(this.onDestroy)).subscribe(evenChecked => {
            if (!this.routeCreateForm.get('allRoad')!.value) {
                if (evenChecked) {
                    this.routeCreateForm.get('roadNumberMinEven')?.enable();
                    this.routeCreateForm.get('roadNumberMaxEven')?.enable();
                    this.routeCreateForm.get('roadNumberMinEven')!.setValue(this.routeCreateForm.get('roadName')!.value.minEven);
                    this.routeCreateForm.get('roadNumberMaxEven')!.setValue(this.routeCreateForm.get('roadName')!.value.maxEven);
                } else {
                    this.routeCreateForm.get('roadNumberMinEven')?.disable();
                    this.routeCreateForm.get('roadNumberMaxEven')?.disable();
                    this.routeCreateForm.get('roadNumberMinEven')!.setValue(null);
                    this.routeCreateForm.get('roadNumberMaxEven')!.setValue(null);
                }
            }
        });
    }




    //Form functions

    submitStreet() {
        const formResult = this.routeCreateForm.getRawValue();
        const road: Road = {
            province: formResult.province,
            town: formResult.town.name,
            postCode: formResult.postCode,
            roadType: formResult.roadType,
            roadName: formResult.roadName.name,
            minOdd: formResult.roadNumberMinOdd,
            maxOdd: formResult.roadNumberMaxOdd,
            minEven: formResult.roadNumberMinEven,
            maxEven: formResult.roadNumberMaxEven
        };

        this.routeCreatorService.addRoad(road);

        //reset form to initial state
        this.routeCreateForm.reset({
            province: 'Madrid',
            town: '',
            postCode: '',
            roadType: '',
            roadName: '',
            roadNumberMinOdd: null,
            roadNumberMaxOdd: null,
            roadNumberMinEven: null,
            roadNumberMaxEven: null,
            allRoad: true,
            odd: true,
            even: true

        }, { emitEvent: false });

    }

}
