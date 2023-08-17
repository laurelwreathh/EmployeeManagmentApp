import React, {Component} from 'react'

class TokenComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            employee: {}
        }

        this.register = this.register.bind(this);
        this.authenticate = this.authenticate.bind(this);
    }

    componentDidMount() {
        return;
    }

    cancel() {
        this.props.history.push('/employees');
    }

    register() {
        this.props.history.push('/register');
    }

    authenticate() {
        this.props.history.push('/authenticate');
    }

    render() {
        return (
            <div>
                <br></br>
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center"> Authentication</h3>
                    <div className="card-body">
                        <button className="btn btn-success" onClick={this.register}>Register
                        </button>

                        <button className="btn btn-success" onClick={this.authenticate}
                                style={{marginLeft: "10px"}}>Authenticate
                        </button>

                        <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                style={{marginLeft: "10px"}}>Cancel
                        </button>
                    </div>

                </div>
            </div>
        )
    }
}

export default TokenComponent
