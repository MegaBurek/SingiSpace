import { Comment } from './comment';
import {Like} from './like';

export class Post {
  title: string;
  textContent: string;
  imgContent?: string;
  comments: Comment[];
  likes: Like[];
}
