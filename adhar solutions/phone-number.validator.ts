import { AbstractControl } from "@angular/forms";

//Validator Class to validate phone number
export class PhoneNumberValidator {

  //method to check whether all digits of phone number are not same
    static noRepeat(control: AbstractControl): {'noRepeat':true} | null {
        if(control && control.value){
          const inp = control.value+"" as string;
          var flag:boolean=false;
         for(let i=0;i<inp.length-1;i++){
            if(inp[i]!=inp[i+1]){
              flag=true;
              break;
            }
         }
        }
        if(flag)
        return null;
        else{
          return {'noRepeat':true}
        }
    }
}
