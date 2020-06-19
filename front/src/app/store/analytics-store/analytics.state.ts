import {Action, Selector, State, StateContext} from '@ngxs/store';
import {AnalyticsService} from '../../services/analytics/analytics.service';
import {GetSocialGroupCount, GetUserRoleCount} from './analytics.actions';
import {tap} from 'rxjs/operators';

export class AnalyticsStateModel {
  userRoleCount: number[];
  socialGroupCount: number[];
}

@State<AnalyticsStateModel>({
  name: 'analytics',
  defaults: {
    userRoleCount: [],
    socialGroupCount: []
  }
})
export class AnalyticsState {
  constructor(
    private analytics: AnalyticsService
  ) {
  }

  @Selector()
  static getallUserRoles(state: AnalyticsStateModel) {
    return state.userRoleCount;
  }

  @Selector()
  static getallSocialGroups(state: AnalyticsStateModel) {
    return state.socialGroupCount;
  }

  @Action(GetUserRoleCount)
  getUserRoleCount({patchState}: StateContext<AnalyticsStateModel>) {
    return this.analytics.getUserRoleStats().pipe(tap((values) => {
      patchState({
        userRoleCount: values
      });
    }));
  }

  @Action(GetSocialGroupCount)
  getSocialGroupCount({patchState}: StateContext<AnalyticsStateModel>) {
    return this.analytics.getSocialGroupCount().pipe(tap((values) => {
      patchState({
        socialGroupCount: values
      });
    }));
  }

  // @Action(GetUserRoleCount)
  // getUserRoleCount({patchState}: StateContext<AnalyticsStateModel>) {
  //   return this.analytics.getUserRoleStats().pipe(tap((values) => {
  //     patchState({
  //       allUserRoles: values
  //     });
  //   }));
  // }
}

