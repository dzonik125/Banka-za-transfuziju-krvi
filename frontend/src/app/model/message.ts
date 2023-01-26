export class Message {
    text: string;
    subject: string;
    imgSrc: string;
    bloodBankApiKey: string;

    public constructor(text:string, subject:string, imgSrc:string, bloodBankApiKey:string){
        this.text = text;
        this.subject = subject;
        this.imgSrc = imgSrc;
        this.bloodBankApiKey = bloodBankApiKey;
    }

}