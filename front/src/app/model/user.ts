import { Permission } from './permission'

export class User{
    id: string
    name: string
    surname: string
    username: string
    password: string
    email: string
    dob: Date
    page_subs: Array<string>
    theme_subs: Array<string>
    imgUrl: string
    permission: Permission
    provider: 'local'
}

// id: string
//     name: string
//     surname: string
//     username: string
//     password: string
//     email: string
//     imgUrl: string
//     permission: Permission