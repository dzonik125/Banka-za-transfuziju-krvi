export class Message {
    text: string;
    subject: string;
    image: string;

    public constructor(text:string, subject:string, image:string){
        this.text = text;
        this.subject = subject;
        this.image = image;
    }

}