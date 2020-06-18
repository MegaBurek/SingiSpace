import {Post} from './post';

export class Page {
  id: string;
  name: string;
  desc: string;
  owner: string;
  categories: string[];
  members: [];
  feed: Post[];
}
