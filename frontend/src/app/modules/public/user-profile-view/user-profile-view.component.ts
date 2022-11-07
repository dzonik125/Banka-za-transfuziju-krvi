import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/user';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../register-user/service/user.service';

@Component({
  selector: 'app-user-profile-view',
  templateUrl: './user-profile-view.component.html',
  styleUrls: ['./user-profile-view.component.css']
})
export class UserProfileViewComponent implements OnInit {
  
  id: any;
  user: User | undefined;
  constructor(private route: ActivatedRoute, private http: HttpClient, private userService: UserService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.userService.fetchUser(this.id).subscribe(data => {
      this.user = new User(data);
    });
  }

}
