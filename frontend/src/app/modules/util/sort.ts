export class MySort {
  isDesc: boolean = false;
  order: boolean = true;
  arrlist: any;


  sortData(list: any){
    this.order = !this.order;
    if(this.order){
      let newarr = list.sort((a: any,b: any) => a.avgGrade - b.avgGrade);
      list = newarr
    }
    else{
      let newarr = list.sort((a: any,b: any) => b.avgGrade - a.avgGrade);
      list = newarr;
    }
  }

sortName(lista: any,property: any){
  this.arrlist = lista;
  this.isDesc = !this.isDesc;

  let direction = this.isDesc ? 1: -1;

    this.arrlist.sort(function (a: any, b:any) {
    switch (property) {
      case 'address.city': {
        if (a.address.city < b.address.city){
          return -1 * direction;
        }
        else if(a.address.city > b.address.city){
          return 1 * direction;
        } else{
          return 0;
        }
      }
      default: {
        if (a[property] < b[property]){
          return -1 * direction;
        }
        else if(a[property] > b[property]){
          return 1 * direction;
        } else{
          return 0;
        } }
    }
  });
}
}
