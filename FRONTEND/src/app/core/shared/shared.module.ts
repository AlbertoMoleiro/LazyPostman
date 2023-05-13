import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { NotFoundComponent } from './components/not-found/not-found.component';
import { CommonModule } from '@angular/common';

@NgModule({
    declarations: [NotFoundComponent],
    imports: [CommonModule, HttpClientModule, RouterModule],
    exports: [HttpClientModule, NotFoundComponent],
})
export class SharedModule {}
