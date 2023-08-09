import React, {Component} from 'react'
import EmployeeService from '../services/EmployeeService';
import AuthenticationService from '../services/AuthenticationService';


class AuthenticateComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            emailId: '',
            password: '',

        }
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.authenticate = this.authenticate.bind(this);
    }

    // step 3
    componentDidMount() {
        return
    }

    authenticate = (e) => {
        e.preventDefault();
        //dto
        let jwtRequest = {emailId: this.state.emailId, password: this.state.password};
        console.log('employee => ' + JSON.stringify(jwtRequest));

            AuthenticationService.authenticate(jwtRequest).then(res => {
                this.props.history.push('/');
            });
    }

    changeEmailHandler = (event) => {
        this.setState({emailId: event.target.value});
    }
    changePasswordHandler = (event) => {
        this.setState({password: event.target.value});
    }

    cancel() {
        this.props.history.push('/');
    }

    getTitle() {
        if (this.state.id === 'authenticate') {
            return <h3 className="text-center">Authenticate</h3>
        } else {
            return <h3 className="text-center">Authenticate</h3>
        }
    }

    render() {
        return (
            <div>
                <br></br>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Email Id: </label>
                                        <input placeholder="Email" name="emailId" className="form-control"
                                               value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                    </div>

                                    <div className="form-group">
                                        <label> Password : </label>
                                        <input placeholder="Password" name="password" className="form-control"
                                               value={this.state.password} onChange={this.changePasswordHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.authenticate}>Save
                                    </button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default AuthenticateComponent
