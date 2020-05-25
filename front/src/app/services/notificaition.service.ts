import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificaitionService {

  constructor(private toastr: ToastrService) { }

  showSuccess(message, title) {

    this.toastr.success(message, title,
      {
        timeOut: 1500,
        positionClass: 'toast-top-left'
      }
    )

  }
  showError(message, title) {

    this.toastr.error(message, title,
      {
        timeOut: 1500,
        positionClass: 'toast-top-right'
      }
    )

  }
  showInfo(message, title) {

    this.toastr.info(message, title)

  }
  showWarning(message, title) {

    this.toastr.warning(message, title)

  }
}
