import { render } from '@testing-library/react';
import { singUp } from '../api/apiCalls';
import React, { Component } from 'react';

class UserSignUpPage extends React.Component {
    state = {
        userName: null,
        fullName: null,
        password: null,
        passwordRetype: null,
        pendingApiCall: null,
        errors: {
        }
    };

    onChange = event => {
        const { name, value } = event.target;
        const errors = { ...this.state.errors }
        errors[name] = undefined;
        this.setState({
            [name]: value,
            errors
        });
    };

    onClickSignUp = async event => {
        event.preventDefault();
        const { userName, fullName, password } = this.state;
        const body = {
            userName,
            fullName,
            password
        }
        this.setState({ pendingApiCall: true });

        try {
            await singUp(body);
        } catch (error) {
            if (error.response.data.validationErrors) {
                this.setState({ errors: error.response.data.validationErrors });
            }
        }
        this.setState({ pendingApiCall: false });
    };
    render() {
        const { pendingApiCall, errors } = this.state;
        const { userName, fullName } = errors;
        return (
            <form className="container">
                <h1 className="text-center">Sign Up</h1>
                <div className="form-group">
                    <label>Username</label>
                    <input className={userName ? "form-control is-invalid" : "form-control"} name="userName" onChange={this.onChange} />
                    <div className="invalid-feedback">{userName}</div>
                </div>
                <div className="col-12 form-group">
                    <label className="form-label">Full Name</label>
                    <input className={fullName ? "form-control is-invalid" : "form-control"} name="fullName" onChange={this.onChange} />
                    <div className="invalid-feedback">{fullName}</div>
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input className="form-control" name="password" type="password" onChange={this.onChange} />
                </div>
                <div className="form-group">
                    <label>Retype Password</label>
                    <input className="form-control" name="passwordRetype" type="password" onChange={this.onChange} />
                </div>
                <div className="text-center">
                    <button disabled={this.pendingApiCall} className="btn btn-primary" onClick={this.onClickSignUp}>
                        {this.pendingApiCall && <span className="spinner-border spinner-border-sm"></span>}
                        Sign Up</button>
                </div>
            </form>
        );
    }
}
export default UserSignUpPage;