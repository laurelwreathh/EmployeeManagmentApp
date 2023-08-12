import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employees";

let config = {
    headers:{
        'Content-type' : 'application/json',
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`
    }
};

class EmployeeService {

    getEmployees() {
        config.headers.Authorization = `Bearer ${sessionStorage.getItem('token')}`
        return axios.get(EMPLOYEE_API_BASE_URL, config);
    }

    createEmployee(employee) {
        return axios.post(EMPLOYEE_API_BASE_URL, employee, config);
    }

    getEmployeeById(employeeId) {
        return axios.get(EMPLOYEE_API_BASE_URL + '/' + employeeId, config);
    }

    updateEmployee(employee, employeeId) {
        return axios.put(EMPLOYEE_API_BASE_URL + '/' + employeeId, employee, config);
    }

    deleteEmployee(employeeId) {
        return axios.delete(EMPLOYEE_API_BASE_URL + '/' + employeeId, config);
    }

}

export default new EmployeeService()