export class LoggedInUser {

    constructor(
        public email: string,
        public password: string,
        public jwt: string,
        public expiresAt: Date,
        public role: string
    ) { }

}