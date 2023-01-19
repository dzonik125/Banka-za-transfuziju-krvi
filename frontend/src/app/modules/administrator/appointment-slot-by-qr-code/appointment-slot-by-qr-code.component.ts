import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import jsQR, { QRCode } from 'jsqr';
import { ExistingAppointmentService } from '../../../services/existing-appointment.service';
import { MedicalExaminationService } from '../../../services/medical-examination.service';

@Component({
  selector: 'app-appointment-slot-by-qr-code',
  templateUrl: './appointment-slot-by-qr-code.component.html',
  styleUrls: ['./appointment-slot-by-qr-code.component.css']
})


export class AppointmentSlotByQrCodeComponent implements OnInit {


  url: any; //Angular 11, for stricter type
	msg = "";
  filss: ImageData | undefined;
  img: HTMLImageElement | undefined;
  qrCodeData: string = '';
  appsl: any;

  constructor(private router: Router,  private medicalExaminationService: MedicalExaminationService, private appointmentService: ExistingAppointmentService) { }

  ngOnInit(): void {
    
  }


  async uploadQRCode(event: any) {
    const file = event.target.files[0];
    const img = await this.createImageBitmap(file) as HTMLImageElement;
    const canvas = document.createElement('canvas');
    canvas.width = img.width;
    canvas.height = img.height;
    const ctx = canvas.getContext('2d');
    if(ctx) {
      ctx.drawImage(img, 0, 0);
      const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
      const qrCode = jsQR(imageData.data, imageData.width, imageData.height);
      if (qrCode) {

          this.qrCodeData = qrCode.data;
      }

    }

  }

  async createImageBitmap(file: any) {
    return new Promise((resolve, reject) => {
        const img = new Image();
        img.src = URL.createObjectURL(file);
        img.onload = () => resolve(img);
        img.onerror = reject;
    });
  }

  sadawd() {
    this.appointmentService.getAppointmentById(this.qrCodeData).subscribe(res => {
      this.appsl = res;
    })
  }

}
