import axios from 'axios';

const EMPLOYEE_API_AUTH_URL = "http://localhost:8080/api/v1/auth";


class AuthenticationService{


    register(jwtRequest) {
        return axios.post(EMPLOYEE_API_AUTH_URL + '/register', jwtRequest).then(res => {
            localStorage.setItem('token', res.data.token);
        }).catch(err => {
            console.log(err);
        });
    }

    authenticate(jwtRequest) {
        return axios.post(EMPLOYEE_API_AUTH_URL + '/authenticate', jwtRequest).then(res => {
            localStorage.setItem('token', res.data.token);
        }).catch(err => {
            console.log(err);
        });
    }
}