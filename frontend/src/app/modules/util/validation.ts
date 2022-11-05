  export class Validator {
    repeatPass = '';

  public checkEmail(email: any){
    var test = email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    var reg = document.getElementById('reg') as HTMLInputElement | null;
    var err = document.getElementById('emailErr') as HTMLInputElement | null;
    if(!test) {
      err?.setAttribute("style", "display: block;");
      err?.setAttribute("style", "color: red;");
      reg?.setAttribute("disabled", "disabled");
    } else {
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
    if(email === ''){
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
  }

  public checkForPassMatch(password: any){
    var err = document.getElementById('err') as HTMLInputElement | null;
    var reg = document.getElementById('reg') as HTMLInputElement | null;
    if(this.repeatPass !== '') {
      if(this.repeatPass !== password) {
        err?.setAttribute("style", "display: block;");
        err?.setAttribute("style", "color: red;");
        reg?.setAttribute("disabled", "disabled");
      } else {
        err?.setAttribute("style", "display: none;");
        reg?.removeAttribute("disabled");
      }
    } else {
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
  }

  public checkForJmbg(jmbg: any){
    var err = document.getElementById('err2') as HTMLInputElement | null;
    var reg = document.getElementById('jmbgErr') as HTMLInputElement | null;
    if(jmbg.length != 13) {
        err?.setAttribute("style", "display: block;");
        err?.setAttribute("style", "color: red;");
        reg?.setAttribute("disabled", "disabled");
      } else {
        err?.setAttribute("style", "display: none;");
        reg?.removeAttribute("disabled");
      }
      if(jmbg === ''){
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
  }

}
