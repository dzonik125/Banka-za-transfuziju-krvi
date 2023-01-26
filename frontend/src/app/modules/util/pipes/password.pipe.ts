import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'password'
})
export class PasswordPipe implements PipeTransform {

  transform(value: string | undefined): any {
    return '*'.repeat(value!.length);
  }

}
