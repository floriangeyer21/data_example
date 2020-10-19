import React, { useState, useEffect, useCallback } from 'react';

import axios from "axios";
import AllUsers from './component/AllUsers';
import UserProfiles from "./component/UserProfiles";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            users:[],
            id:0,
            firstName:'',
            lastName:''
        }
    }
    componentDidMount() {
        axios.get("http://localhost:8080/user/all/").then((res) => {
            this.setState({
                    users:res.data,
                    id:0,
                    firstName:'',
                    lastName:''
                }
            )
        })
    }

    submit(event, id) {
        event.preventDefault();
        if (id === 0) {
            axios.post("http://localhost:8080/user/all",{
                firstName:this.state.firstName,
                lastName:this.state.lastName
            })
                .then((res) => {
                    this.componentDidMount();
                })
        } else {
            axios.put("http://localhost:8080/user/all", {
                id:this.state.id,
                firstName:this.state.firstName,
                lastName:this.state.lastName
            })
                .then((res) => {
                    this.componentDidMount();
                });
        }
    }

    delete(id) {
        axios.delete(`http://localhost:8080/user/${id}`)
            .then(() => {
                this.componentDidMount();
            })
    }

    edit(id) {
        axios.get(`http://localhost:8080/user/${id}`)
            .then((res) => {
                console.log(res.data);
                this.setState({
                    id:this.data.id,
                    firstName:this.data.firstName,
                    lastName:this.data.lastName
                })
            })
    }

    render() {
        return (
            <div className={"App"}>
                <div className={"row"}>
                    <div className="col s6">
                        <form onSubmit={event => this.state.submit(event, this.state.id)}>
                            <div className="input-field col s12">
                                <i className="material-icons prefix">firstname</i>
                                <input onChange={event => this.setState({firstName:event.target.value})} value={this.state.firstName} type="text" id="autocomplete-input" className="autocomplete" />
                                <label htmlFor="autocomplete-input">Autocomplete</label>
                            </div>
                            <div className="input-field col s12">
                                <i className="material-icons prefix">lastname</i>
                                <input onChange={event => this.setState({s:event.target.value})} value={this.state.lastName} type="text" id="autocomplete-input" className="autocomplete" />
                                <label htmlFor="autocomplete-input">Autocomplete</label>
                            </div>
                            <button>
                                <a className="waves-effect waves-light btn"><i className="material-icons right">cloud</i>button</a>
                            </button>
                        </form>
                    </div>
                    <div className="col s6">
                        <table>
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            </thead>

                            <tbody>
                            {
                                Array.from(this.state.users).map(user =>
                                    <tr key={user.id}>
                                        <td>{user.firstName}</td>
                                        <td>{user.lastName}</td>
                                        <button onClick={(e) => this.edit(user.id)} type={"submit"} name={"action"}>
                                            <a className="waves-effect waves-light btn"><i className="material-icons right">edit</i>edit</a>
                                        </button>
                                        <button onClick={(e) => this.delete(user.id)} type={"submit"} name={"action"}>
                                            <a className="waves-effect waves-light btn"><i className="material-icons right">cloud</i>delete</a>
                                        </button>
                                    </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                    <div className="App">
                        <UserProfiles />
                        <AllUsers />
                    </div>
                </div>
            </div>
        );
    }
}

export default App;


