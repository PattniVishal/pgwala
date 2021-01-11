export class RegisterDTO{

    constructor(
        private name: string,
        private email: string,
        private password: string,
        private role: string,
        private aadharNumber?: string
    ){}

}