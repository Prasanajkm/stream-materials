import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'aadharNumber'
})
//Custom pipe class to transform the aadhar number in a specified format <4 digits>-<4 digits>-<4 digits>
export class AadharNumberPipe implements PipeTransform {

  transform(aadharNo: string): string {
    if(aadharNo){
      return aadharNo.slice(0,4)+"-"+aadharNo.slice(4,8)+"-"+aadharNo.slice(8,12);
    }else{
      return null;
    }
  }

}
