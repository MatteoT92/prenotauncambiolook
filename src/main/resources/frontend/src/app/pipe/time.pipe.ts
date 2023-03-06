import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'time'
})
export class TimePipe implements PipeTransform {

  transform(value: Date, ...args: unknown[]): string {
    let time = value.toString().split(',');
    console.log(time);
    let ore = time[0];
    let minuti = time[1];
    if (ore.length < 2) {
      ore = '0' + ore;
    }
    if (minuti.length < 2) {
      minuti = '0' + minuti;
    }
    return ore + ':' + minuti;
  }

}
