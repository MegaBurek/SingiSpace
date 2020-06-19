import {Post} from './post';

export class Theme {
  id: string;
  name: string;
  desc: string;
  owner: string;
  imgUrl: string;
  categories: string[];
  members: [];
  feed: Post[];
}
