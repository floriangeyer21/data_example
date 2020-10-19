import React, { Component } from 'react';

class User extends Component {


    render() {
        return (
            <div className="container">
                <h3>All user</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Alice</td>
                                <td>Bobson</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }

}

/*const UserProfiles = () => {
    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles = () => {
        axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
            setUserProfiles(res.data);
        });
    }

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return userProfiles.map((userProfile, index) => {
        return (
            <div key={index}>
                {*//* todo: profile image *//*}
                <br />
                <br />
                <h1>{userProfile.userName}</h1>
                <p>{userProfile.userProfileId}</p>
                <Dropzone {...userProfile} />
                <br />
            </div>
        )
    });
}*/

export default User
