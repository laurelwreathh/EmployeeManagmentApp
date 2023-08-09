import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employees";
let headers = {Authorization: `Bearer ${localStorage.getItem('token')}`};


class EmployeeService {

    getEmployees() {
        return axios.get(EMPLOYEE_API_BASE_URL, {headers: headers});
    }

    createEmployee(employee) {
        return axios.post(EMPLOYEE_API_BASE_URL, employee, {headers: headers});
    }

    getEmployeeById(employeeId) {
        return axios.get(EMPLOYEE_API_BASE_URL + '/' + employeeId, {headers: headers});
    }

    updateEmployee(employee, employeeId) {
        return axios.put(EMPLOYEE_API_BASE_URL + '/' + employeeId, employee, {headers: headers});
    }

    deleteEmployee(employeeId) {
        return axios.delete(EMPLOYEE_API_BASE_URL + '/' + employeeId, {headers: headers});
    }

}

export default new EmployeeService()