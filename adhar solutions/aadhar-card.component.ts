import { Component, OnInit } from '@angular/core';
import { AadharCardService } from './aadhar-card.service';
import { AadharCard } from './aadhar-card';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PhoneNumberValidator } from './phone-number.validator';
@Component({
  selector: 'app-aadhar-card',
  templateUrl: './aadhar-card.component.html',
  styleUrls: ['./aadhar-card.component.css']
})
export class AadharCardComponent implements OnInit {

  //errorMessage instance variable to store the message returned from the response returned from server
  errorMessage: string;
  //successMessage instance variable to populate successMessage
  successMessage: string;
  //myAadhar instance variable to store the AadharCard object returned from response
  myAadhar: AadharCard;
  //getAadharForm variable to group the form control aadharNumber
  getAadharForm: FormGroup;
  //updatePhoneNumberForm variable to group the formcontrols aadharNumber and phoneNumber
  updatePhoneNumberForm: FormGroup;

  //Inject AadharCardService and FormBuilder object to the constructor
  constructor(
    public aadharCardService: AadharCardService,
    public formBuilder: FormBuilder
  ) { }

  //Initialize getAadharForm form field aadharNumber
  ngOnInit() {
    this.getAadharForm = this.formBuilder.group({
      'aadharNumber': ['', [Validators.required, Validators.pattern('[0-9]{12}')]]//binding mandatory and pattern validation for aadharNumber
    });

  //Initialize updatePhoneNumberForm form fields with aadharNumber and phoneNumber as attributes
    this.updatePhoneNumberForm = this.formBuilder.group({
      'aadharNumber': ['', [Validators.required]],//binding mandatory validation for aadharNumber
      'phoneNumber': ['', [Validators.required, PhoneNumberValidator.noRepeat,Validators.min(1000000000),Validators.max(9999999999)]]//binding mandatory and custom phonenumber validation for phoneNumber
    });
  }

  //This method is to retrieve aadhar card details by invoking getAadhar method of service
  getAadhar() {
    this.successMessage = null;
    this.errorMessage = null;
    this.myAadhar = null;
    //Call getAadhar of aadharCardService by passing the getAadharForm value
    this.aadharCardService.getAadhar(this.getAadharForm.value)
      .subscribe(
        (response) => {
          this.myAadhar = response;
          //populate aadharNumber and phoneNumber of updatePhoneNumberForm returned from server response
          this.updatePhoneNumberForm.get('aadharNumber').setValue(response.aadharNumber);
          this.updatePhoneNumberForm.get('phoneNumber').setValue(response.phoneNumber);
        },
        (response) => {
          //populate errormessage of updatePhoneNumberForm returned from server response
          this.errorMessage = response.error.message;
        }
      );
  }

  //This method is to update phone number and retrieve the successMessage, errorMessage from the response returned
  updatePhoneNumber() {

    this.successMessage = null;
    this.errorMessage = null;
    this.aadharCardService.updatePhoneNumber(this.updatePhoneNumberForm.value)
      .subscribe(
        (response) => {
         
            //populate successMessage of updatePhoneNumberForm 
            
          this.successMessage = "Phone number updated successfully as "+response.phoneNumber;
        },
        (response) => {
          
          //populate errorMessage of updatePhoneNumberForm returned from server response
          this.errorMessage = response.error.message;
        }
      );
  }
}
