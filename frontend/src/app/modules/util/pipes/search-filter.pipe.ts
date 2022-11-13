import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {

  transform(value: any, filteredString: string) {
    if(value.length === 0 || filteredString === '') {
      return value;
    }

    const medicalWorkers = [];
   
    
    if(filteredString.trim().includes(" ")){
      const splited = filteredString.split(" ");
      
      for(const user of value) {
        if(user['name'].toLowerCase().includes(splited[0]) && user['surname'].toLowerCase().includes(splited[1]) ) {
          medicalWorkers.push(user);
        }
        if(user['name'].toLowerCase().includes(splited[1]) && user['surname'].toLowerCase().includes(splited[0]) ) {
          medicalWorkers.push(user);
        }
        
      }

    }
    else {
      for(const user of value) {
        if(user['name'].toLowerCase().includes(filteredString.trim()) || user['surname'].toLowerCase().includes(filteredString) ) {
          medicalWorkers.push(user);
        }
      }
    }
    return medicalWorkers;
  }
}
